package com.arisurya.jetpackpro.canangbali.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "upakaraentities")
data class UpakaraEntity(
    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "upakaraId")
    var upakaraId : Int,

    @ColumnInfo(name = "title")
    var title : String,

    @ColumnInfo(name = "imgPath")
    var imgPath : String,

    @ColumnInfo(name = "desc")
    var desc : String,

    @ColumnInfo(name = "bookmarked")
    var bookmarked : Boolean = false
)
