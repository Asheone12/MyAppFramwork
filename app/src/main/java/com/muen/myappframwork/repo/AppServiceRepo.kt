package com.muen.myappframwork.repo

import com.muen.myappframwork.entity.Word
import com.muen.myappframwork.http.CommonHandler
import com.muen.myappframwork.http.httpCollect
import com.muen.myappframwork.http.httpFlow
import com.muen.myappframwork.source.network.AppServiceApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppServiceRepo @Inject constructor(
    private val service: AppServiceApi
) {
    suspend fun randomWord(handler: CommonHandler<Word>) {
        httpFlow {
            service.randomWord("json")
        }.httpCollect(handler)
    }
}