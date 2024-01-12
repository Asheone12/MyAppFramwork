package com.muen.myappframwork.source.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.muen.myappframwork.source.local.entity.WordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    /**
     * 查询所有一言
     * Flow中的collect本身就是一个挂起函数，所以不需要额外用suspend修饰
     */
    @Query("select * from T_Word")
    fun loadWords(): Flow<List<WordEntity>>

    /**
     * 查询指定一言
     * Flow中的collect本身就是一个挂起函数，所以不需要额外用suspend修饰
     */
    @Query("select * from T_Word where wid = :wid")
    fun findWord(wid: String): Flow<WordEntity>

    /**
     * 插入一言,key键重复的替换
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(vararg word: WordEntity)

    /**
     * 删除一言，Room 使用主键将传递的实体实例与数据库中的行进行匹配。如果没有具有相同主键的行，Room 不会进行删除
     */
    @Delete
    suspend fun deleteWord(word: WordEntity)

    /**
     * 更新一言，Room 使用主键将传递的实体实例与数据库中的行进行匹配。如果没有具有相同主键的行，Room 不会进行任何更改
     */
    @Update
    suspend fun updateWord(vararg word: WordEntity)
}