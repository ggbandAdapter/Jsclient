package com.starteos.jsbridge.tron.api

import cn.ggband.jsclient.client.Call
import cn.ggband.jsclient.client.anno.Field
import cn.ggband.jsclient.client.anno.PartMap
import com.starteos.jsbridge.tron.model.Result
import com.starteos.jsbridge.tron.model.TronAccount

internal interface TrxApi {

    fun getAccount(address: String): Call<Result<TronAccount>>

    fun getBalance(address: String): Call<Result<String>>


    /**
     * 转账
     */
    fun sendTRX(@PartMap("action") action: Map<String, String>, @Field("privateKey") privateKey: String): Call<Result<Any>>
}
