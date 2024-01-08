package com.muen.myappframwork.source.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.muen.myappframwork.source.local.dao.WordDao
import com.muen.myappframwork.source.local.entity.WordEntity

@Database(version = 1, entities = [WordEntity::class])
abstract class TenApiDB: RoomDatabase() {
    abstract fun wordDao(): WordDao
    companion object {
        private var instance: TenApiDB? = null

        @Synchronized
        fun getDatabase(context: Context): TenApiDB {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(
                context.applicationContext,
                TenApiDB::class.java,
                "tenApi.db"
            )//.createFromAsset("database/tenApi.db")        //如果需要手动添加数据库文件，加上这一句话
                .build().apply {
                    instance = this
                }
        }
    }
}