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
        wordDao.loadWords().collect {
            handler.invoke(it)
        }
    }

    /**
     * 向数据库中插入一条一言
     */
    suspend fun insertWord(word: WordEntity) {
        wordDao.insertWord(word)
    }

    /**
     * 在数据库中删除一条一言
     */
    suspend fun deleteWord(word: WordEntity) {
        wordDao.deleteWord(word)
    }

    /**
     * 在数据库中更新一条一言
     */
    suspend fun updateWord(word: WordEntity) {
        wordDao.updateWord(word)
    }

}