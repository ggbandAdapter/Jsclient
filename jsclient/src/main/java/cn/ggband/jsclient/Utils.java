package cn.ggband.jsclient;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cn.ggband.jsclient.client.anno.Field;
import cn.ggband.jsclient.client.anno.PartMap;
import cn.ggband.jsclient.client.anno.RequestBody;


public class Utils {

    public static final String TAG = "JS_BRIDGET";

    public static <T> void validateServiceInterface(Class<T> service) {
        if (!service.isInterface()) {
            throw new IllegalArgumentException("API declarations must be interfaces.");
        }
        // Prevent API interfaces from extending other interfaces. This not only avoids a bug in
        // Android (http://b.android.com/58753) but it forces composition of API declarations which is
        // the recommended pattern.
        if (service.getInterfaces().length > 0) {
            throw new IllegalArgumentException("API interfaces must not extend other interfaces.");
        }
    }


    /**
     * 构建参数
     *
     * @param method 方法
     * @param args   参数
     * @return 参数
     */
    public static String parseParameter(Method method, Object[] args) {

        Class[] parameterTypes = method.getParameterTypes();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        int parameterTypesLength = parameterTypes.length;
        if (parameterTypesLength == 0) return "{}";

        Object[] values = (Object[]) args[0];
        //只有一个参数时
        if (parameterTypes.length == 1) {
            Object value = values[0];
            Annotation annotation = getParameterAnnotation(0, parameterAnnotations, false);
            if (annotation instanceof RequestBody) {
                return new Gson().toJson(value);
            } else if (parameterTypes[0] == Map.class) {
                Map map = (Map) value;
                return new Gson().toJson(map);
            } else {
                return value.toString();
            }
        }

        //参数结果
        Map<Object, Object> resultMap = new HashMap<>();

        Annotation[] targetAnnotations = new Annotation[parameterAnnotations.length];

        for (int i = 0; i < parameterAnnotations.length; i++) {
            Annotation annotation = getParameterAnnotation(i, parameterAnnotations, true);
            targetAnnotations[i] = annotation;
        }
        for (int i = 0; i < parameterTypes.length; i++) {
            if (targetAnnotations[i] instanceof Field) {
                Field field = (Field) targetAnnotations[i];
                resultMap.put(field.value(), values[i]);

            } else if (targetAnnotations[i] instanceof PartMap) {
                if (parameterTypes[i] != Map.class) {
                    throw new IllegalArgumentException("Partmap annotation must decorate parameters of map type");
                }
                PartMap partMap = (PartMap) targetAnnotations[i];
                Map map = (Map) values[i];
                if (partMap.value().isEmpty()) {
                    resultMap.putAll(map);
                } else {
                    resultMap.put(partMap.value(), map);
                }

            } else {
                //RequestBody 注解
                RequestBody requestBody = (RequestBody) targetAnnotations[i];
                if (requestBody.value().isEmpty()) {
                    String bodyJsonStr = new Gson().toJson(values[i]);
                    try {
                        JSONObject bodyJs = new JSONObject(bodyJsonStr);
                        Iterator<String> iterator = bodyJs.keys();
                        while (iterator.hasNext()) {
                            String key = iterator.next();
                            resultMap.put(key, bodyJs.get(key));
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    resultMap.put(requestBody.value(), values[i]);
                }
            }
        }

        return new Gson().toJson(resultMap);
    }


    /**
     * 获取API方法的注解
     *
     * @param index            方法上第几个参数
     * @param annotationCounts 方法的注解集合
     * @param isCheck          是否检测api注解
     * @return 业务自身的api注解
     */
    private static Annotation getParameterAnnotation(int index, Annotation[][] annotationCounts, boolean isCheck) {
        Annotation[] annotations = annotationCounts[index];
        boolean checkedEnable = checkParameterAnnotation(annotations);

        if (isCheck) {
            if (!checkedEnable) {
                throw new RuntimeException("Method with multiple parameters must have one and only one API annotation for each parameter");
            }
        }
        if (!checkedEnable)
            return null;

        Annotation targetAnnotation = null;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Field) {
                targetAnnotation = annotation;
                break;
            } else if (annotation instanceof PartMap) {
                targetAnnotation = annotation;
                break;
            } else if (annotation instanceof RequestBody) {
                targetAnnotation = annotation;
                break;
            }
        }

        return targetAnnotation;

    }


    /**
     * 检测方法参数是否合法
     */
    private static Boolean checkParameterAnnotation(Annotation[] annotations) {

        //1、同一个参数有且只有一个api注解
        int apiCount = 0;
        for (Annotation annotation : annotations) {
            if (annotation instanceof Field) {
                apiCount++;
            }
            if (annotation instanceof PartMap) {
                apiCount++;
            }
            if (annotation instanceof RequestBody) {
                apiCount++;
            }
        }
        return apiCount == 1;
    }


}
