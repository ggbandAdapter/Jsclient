package com.starteos.jsbridge.jsbridge.client

import android.content.Context
import android.webkit.WebView
import cn.ggband.jsclient.BridgeWebView
import cn.ggband.jsclient.Utils
import cn.ggband.jsclient.client.Platform
import java.lang.reflect.Proxy

//https://blog.csdn.net/u011479200/article/details/90260051
//https://www.jianshu.com/p/ef8734671763


/**
 * JsClient
 */
@Suppress("UNCHECKED_CAST")
class CallJsClient private constructor(builder: Builder) {


    private var mWebView: BridgeWebView = BridgeWebView(builder.getContext())

    private var mContext: Context? = null
    /**
     * html的url
     */
    private var url: String? = null


    init {
        mContext = builder.getContext()
        url = builder.getUrl()
        mWebView.loadUrl(url)
    }


    fun <T> create(service: Class<T>): T {
        Utils.validateServiceInterface(service)
        return Proxy.newProxyInstance(
            service.classLoader,
            arrayOf<Class<*>>(service)
        ) { proxy, method, args ->
            val platform = Platform.get(mWebView)
            platform.invokeDefaultMethod(method, service, proxy, args)
        } as T
    }


    fun getWebView(): BridgeWebView {
        return mWebView
    }


    class Builder {

        private var context: Context? = null

        private var url: String? = null


        fun getUrl() = url

        fun getContext() = context


        fun url(url: String): Builder {
            this.url = url
            return this
        }

        fun context(context: Context): Builder {
            this.context = context
            return this
        }


        fun build(): CallJsClient {
            return CallJsClient(this)
        }
    }


}
