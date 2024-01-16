package com.muen.myappframwork.source.network

import com.muen.myappframwork.source.network.entity.Word
import com.muen.myappframwork.http.HttpResult
import com.muen.myappframwork.source.network.entity.ACGImg
import retrofit2.http.GET
import retrofit2.http.Query


interface AppServiceApi {
    /**
     * 随机一言
     */
    @GET("yiyan")
    suspend fun randomWord(@Query("format") format: String): HttpResult<Word>

    /**
     * 随机动漫图片
     */
    @GET("acg")
    suspend fun randomACG(@Query("format") format: String): HttpResult<ACGImg>
}