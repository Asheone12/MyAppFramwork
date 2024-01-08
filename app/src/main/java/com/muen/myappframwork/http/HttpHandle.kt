package com.muen.myappframwork.http

import android.util.Log

interface HttpResultHandler<T> {
    suspend fun onDataResult(data: T?)
    suspend fun onCodeResult(code: Int, msg: String?)
    suspend fun onErrorResult(throwable: Throwable)
}

abstract class CommonHandler<T> : HttpResultHandler<T> {
    override suspend fun onDataResult(data: T?) {

    }

    override suspend fun onCodeResult(code: Int, msg: String?) {
        Log.d("handle","code=$code,message=$msg")
    }

    override suspend fun onErrorResult(throwable: Throwable) {
        Log.d("handle","throwable=${throwable.toString()}")
    }
}