package com.starteos.jsbridge.tron

import cn.ggband.jsclient.client.Callback
import com.starteos.jsbridge.tron.model.Result

abstract class TronCallback<T> : Callback<Result<T>> {

    override fun onResponse(result: Result<T>) {
        if (result.status) {
            onCall(result.data)
        } else {
            onFailure(result.message)
        }

    }

    override fun onFailure(t: Throwable) {
        onFailure(t.message)
    }


    internal abstract fun onCall(data: T?)

    internal abstract fun onFailure(message: String?)


}
