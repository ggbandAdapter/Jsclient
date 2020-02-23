package com.starteos.jsbridge

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.starteos.jsbridge.jsbridge.client.CallJsClient
import com.starteos.jsbridge.tron.TronCallback
import com.starteos.jsbridge.tron.api.TrxApi
import com.starteos.jsbridge.tron.model.TronAccount
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val mAddress = "TYFNbrpmTbCLumjHWdZCn6LjJa2iBiM6bG"
    private val privateKey = "D9FCF09653C8D10E4DA3132379525252C76C6B633E5EE98F3A769CDF11277828"
    private val toAddress = "TJiNL6g23Rssnbkrxb3d7u5updk6o7V2P2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()

    }


    private val client: Pair<TrxApi, CallJsClient> by lazy {
        val tronClient =
            CallJsClient.Builder().context(this).url("file:///android_asset/TronWeb.html")
                .build()
        val trxApi = tronClient.create(TrxApi::class.java)
        Pair(trxApi, tronClient)
    }


    private fun initData() {
        btnAccount.setOnClickListener {
            client.first.getAccount(mAddress).enqueue(object : TronCallback<TronAccount>() {
                override fun onCall(data: TronAccount?) {
                    toast(Gson().toJson(data))
                }

                override fun onFailure(message: String?) {
                    toast(message!!)
                }
            })

        }

        btnBalance.setOnClickListener {

            client.first.getBalance(mAddress).enqueue(object : TronCallback<String>() {
                override fun onCall(data: String?) {
                    toast(data!!)
                }

                override fun onFailure(message: String?) {
                    toast(message!!)
                }
            })


        }

        btnTx.setOnClickListener {
            val action = mapOf(
                Pair("from", mAddress),
                Pair("to", toAddress),
                Pair("amount", "1 TRX")
            )
            client.first.sendTRX(action, privateKey).enqueue(object : TronCallback<Any>() {
                override fun onCall(data: Any?) {
                    toast(data!!.toString())
                }

                override fun onFailure(message: String?) {
                    toast(message!!)
                }
            })
        }

        //注册 submitFromWeb()给js调用
        client.second.getWebView().registerHandler(
            "submitFromWeb"
        ) { data, function ->
            toast("handler = submitFromWeb, data from web = $data")
            function.onCallBack("submitFromWeb exe, response data 中文 from Java")
        }

    }


    fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }


}
