package com.arisurya.jetpackpro.canangbali.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.CanangEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.PhilosophyEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.ShopEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.UpakaraEntity

@Database(
    entities = [CanangEntity::class, UpakaraEntity::class, ShopEntity::class, PhilosophyEntity::class],
    version = 1, exportSchema = false
)
abstract class CanangDatabase : RoomDatabase() {
    abstract fun canangDao(): CanangDao

    companion object {
        @Volatile
        private var INSTANCE: CanangDatabase? = null

        fun getInstance(context: Context): CanangDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    CanangDatabase::class.java,
                    "Canang.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}