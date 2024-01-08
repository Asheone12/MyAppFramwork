package com.muen.myappframwork.repo

import com.muen.myappframwork.http.CommonHandler
import com.muen.myappframwork.http.httpCollect
import com.muen.myappframwork.http.httpFlow
import com.muen.myappframwork.source.local.dao.WordDao
import com.muen.myappframwork.source.local.entity.WordEntity
import com.muen.myappframwork.source.network.AppServiceApi
import com.muen.myappframwork.source.network.entity.Word
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppServiceRepo @Inject constructor(
    private val service: AppServiceApi,
    private val wordDao: WordDao
) {

    /**
     * 获取一言
     */
    suspend fun randomWord(handler: CommonHandler<Word>) {
        httpFlow {
            service.randomWord("json")
        }.httpCollect(handler)
    }

    /**
     * 查询数据库中的一言
     */
    suspend fun findWords(handler: (List<WordEntity>) -> Unit) {
        wordDao.loadWords().collect{
            handler.invoke(it)
        }
    }

    /**
     * 插入一条一言到数据库
     */
    suspend fun insertWord(word: WordEntity){
         wordDao.insertWord(word)
    }

}