package cn.ggband.jsclient.client;

import android.util.Log;

import androidx.annotation.Nullable;

import com.google.gson.Gson;


import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import cn.ggband.jsclient.BridgeWebView;
import cn.ggband.jsclient.CallBackFunction;

public class Platform {


    private BridgeWebView mWebView;


    private Platform(BridgeWebView webView) {
        this.mWebView = webView;
    }

    public static Platform get(BridgeWebView webView) {
        return new Platform(webView);
    }


    @Nullable
    public Object invokeDefaultMethod(Method method, Class<?> declaringClass, Object object,
                                      @Nullable Object... args) {

        // 获取返回值类型
        Type type = method.getGenericReturnType();
        // 获取返回值类型 name
        Class returnClass = method.getReturnType();
        //返回类型不能处理抛出返回类型错误异常
        if (returnClass.getName().equals("void")) {
            return null;
        } else if (returnClass != Call.class) {
            throw new RuntimeException("Return value type must be" + Call.class.getName());
        }


        Type parseType = null;
        //  type instanceof Class 没有解析类型 Call<*>/Call
        if (type instanceof ParameterizedType) {
            // 判断获取的类型是否是参数类型;
            // 强制转型为带参数的泛型类型，
            Type[] types = ((ParameterizedType) type).getActualTypeArguments();
            // getActualTypeArguments()方法获取类型中的实际类型，如map<String,Integer>中的
            // String，integer因为可能是多个，所以使用数组
            //Call的 actualTypeArguments 最多只有一个 Call<*>
            if (types.length > 0) {
                parseType = types[0];
            }
        }
        String requestParams = Utils.parseParameter(method, args);
        Log.d(Utils.TAG, "requestParams:" + requestParams);
        return new ProxyCall(method.getName(), requestParams, parseType);
    }


    /**
     * api 回调代理
     */
    private class ProxyCall implements Call<Object> {

        // js方法名字
        private String handlerName;
        //js方法参数
        private String data;

        //json 解析 type
        private Type type;

        ProxyCall(String handlerName, String data, Type type) {
            this.handlerName = handlerName;
            this.data = data;
            this.type = type;
        }

        @Override
        public void enqueue(final Callback<Object> callback) {

            mWebView.callHandler(handlerName, data, new CallBackFunction() {
                @Override
                public void onCallBack(String data) {
                    Log.d(Utils.TAG, "request result:" + data);
                    if (type == null) {
                        callback.onResponse(data);
                    } else {
                        Object result = new Gson().fromJson(data, type);
                        callback.onResponse(result);
                    }
                }
            });
        }
    }


}
