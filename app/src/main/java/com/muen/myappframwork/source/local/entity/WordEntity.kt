package com.muen.myappframwork.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "T_Word")
data class WordEntity(
    @PrimaryKey var wid: String,
    var word: String?,
    var author: String?,
    var source: String?,
    var date: String?
)
