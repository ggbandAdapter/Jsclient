# Jsclient
Android and JS interactive tools.

[![Release](https://jitpack.io/v/ggbandAdapter/Jsclient.svg)](https://jitpack.io/#ggbandAdapter/Jsclient)
[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
![SDK](https://img.shields.io/badge/SDK-15%2B-green.svg)

--

## How to introduce dependency

To get a Git project into your build:

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.ggbandAdapter:Jsclient:1.0.1'
	}


--

## How to use

First, create a client that interacts with JS.

   val tronClient = CallJsClient.Builder().context(this).url("file:///android_asset/tron/TronWeb.html").build()
   val trxApi = tronClient.create(TrxApi::class.java)
   
# --- Android calls JS method.

Step 1. Writing java interface calling JS method
   
  internal interface TrxApi {

    /**
     * Access to account information
     */
    fun getAccount(address: String): Call<Result<TronAccount>>

    /**
     * Get account balance
     */

    fun getBalance(address: String): Call<Result<String>>

    /**
     * Transfer
     */
    fun sendTRX(@PartMap("action") action: Map<String, String>, @Field("privateKey") privateKey: String): Call<Result<Any>>
  }

Step 2. Write JS function for Android to call.

    bridge.registerHandler("getAccount", function (data, responseCallback) {
                tronWeb.trx.getAccount(data).then(data => {
                    responseCallback({
                        "status": true,
                        "data": data
                    });
                }).catch(error => {
                    responseCallback({
                        "status": false,
                        "message": JSON.stringify(error)
                    });
                });
            });

     bridge.registerHandler("getBalance", function (data, responseCallback) {
                tronWeb.trx.getBalance(data).then(respone => {
                    responseCallback({
                        "status": true,
                        "data": respone
                    });
                }).catch(error => {
                    responseCallback({
                        "status": false,
                        "message": JSON.stringify(error)
                    });
                });
            });

     bridge.registerHandler("sendTRX", function (data, responseCallback) {
                const app = async () => {
                    let action = data['action'];
                    let privateKey = data['privateKey'];
                    let trx = await tronWeb.transactionBuilder.sendTrx(action['to'],
                        action['amount'], action['from'])
                    let sign = await tronWeb.trx.sign(trx, privateKey)
                    let transaction = await tronWeb.trx.sendRawTransaction(
                        sign)
                    return transaction
                };
                app().then((result) => {
                    responseCallback({
                        "status": true,
                        "data": result
                    });
                }).catch((error) => {
                    responseCallback({
                        "status": false,
                        "message": error
                    });
                });

            });
	    
# For more details, please see ![example](https://github.com/ggbandAdapter/Jsclient/tree/master/app)
