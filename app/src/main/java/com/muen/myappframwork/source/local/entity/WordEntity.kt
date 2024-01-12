package com.muen.myappframwork.source.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "T_Word")
data class WordEntity(
    @PrimaryKey var wid: String,
    var word: String?,
    var author: String?,
    var source: String?,
    var date: String?
):Parcelable
