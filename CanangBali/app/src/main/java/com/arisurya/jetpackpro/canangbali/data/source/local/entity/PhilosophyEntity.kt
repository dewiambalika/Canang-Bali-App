package com.arisurya.jetpackpro.canangbali.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "philosophyentities")
data class PhilosophyEntity (
    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "philosophyId")
    var philosophyId : Int,

    @ColumnInfo(name = "title")
    var title : String,

    @ColumnInfo(name = "imgPath")
    var imgPath : String,

    @ColumnInfo(name = "desc")
    var desc : String
)