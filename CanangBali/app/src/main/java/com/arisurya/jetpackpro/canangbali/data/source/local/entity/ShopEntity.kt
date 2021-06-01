package com.arisurya.jetpackpro.canangbali.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "shopentities")
data class ShopEntity(
    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "shopId")
    var shopId : Int,

    @ColumnInfo(name ="name")
    var name : String,

    @ColumnInfo(name = "imgPath")
    var imgPath : String,

    @ColumnInfo(name ="location")
    var location : String,

    @ColumnInfo(name = "tlp")
    var tlp : String,

    @ColumnInfo(name = "product")
    var product : String,

    @ColumnInfo(name ="desc")
    var desc : String,

    @ColumnInfo(name = "bookmarked")
    var bookmarked : Boolean = false
)
