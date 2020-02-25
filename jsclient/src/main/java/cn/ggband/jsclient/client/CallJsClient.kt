package com.starteos.jsbridge.jsbridge.client

import android.content.Context
import cn.ggband.jsclient.BridgeWebView
import cn.ggband.jsclient.client.Utils
import cn.ggband.jsclient.client.Platform
import java.lang.reflect.Proxy

//https://blog.csdn.net/u011479200/article/details/90260051
//https://www.jianshu.com/p/ef8734671763


/**
 * JsClient
 */
@Suppress("UNCHECKED_CAST")
class CallJsClient private constructor(builder: Builder) {


    private var mWebView: BridgeWebView?

    private var mContext: Context? = null


    init {
        mContext = builder.getContext()
        mWebView = builder.getWebView()
        if (mWebView == null) {
            mWebView = BridgeWebView(builder.getContext())
        }
        builder.getUrl()?.let {
            mWebView?.loadUrl(it)
        }
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


    fun getWebView(): BridgeWebView? {
        return mWebView
    }


    class Builder {

        private var context: Context? = null

        private var url: String? = null

        private var webView: BridgeWebView? = null


        fun getContext() = context

        fun getUrl() = url

        fun getWebView() = webView


        fun context(context: Context): Builder {
            this.context = context
            return this
        }

        fun url(url: String): Builder {
            this.url = url
            return this
        }

        fun webView(webView: BridgeWebView): Builder {
            this.webView = webView
            return this
        }


        fun build(): CallJsClient {
            return CallJsClient(this)
        }
    }


}
