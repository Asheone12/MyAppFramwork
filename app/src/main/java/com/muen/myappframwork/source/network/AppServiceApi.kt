package com.muen.myappframwork.source.network

import com.muen.myappframwork.source.network.entity.Word
import com.muen.myappframwork.http.HttpResult
import retrofit2.http.GET
import retrofit2.http.Query


interface AppServiceApi {
    /**
     * 随机一言
     */
    @GET("yiyan")
    suspend fun randomWord(@Query("format") format: String): HttpResult<Word>
}