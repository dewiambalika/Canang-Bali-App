package com.arisurya.jetpackpro.canangbali.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "canangentities")
data class CanangEntity(
    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "canangId")
    var canangId : Int,

    @ColumnInfo(name = "title")
    var title : String,

    @ColumnInfo(name = "imgPath")
    var imgPath : String,

    @ColumnInfo(name = "function")
    var function : String,

    @ColumnInfo(name = "make")
    var make : String,

    @ColumnInfo(name = "bookmarked")
    var bookmarked : Boolean = false
)
