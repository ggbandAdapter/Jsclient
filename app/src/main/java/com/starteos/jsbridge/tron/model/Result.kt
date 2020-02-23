package com.starteos.jsbridge.tron.model

data class Result<T>(
    var status: Boolean = false,
    var data: T? = null,
    var message: String = "111"
)



