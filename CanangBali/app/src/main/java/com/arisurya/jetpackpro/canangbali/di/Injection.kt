package com.arisurya.jetpackpro.canangbali.di

import android.content.Context
import com.arisurya.jetpackpro.canangbali.data.source.CanangRepository
import com.arisurya.jetpackpro.canangbali.data.source.local.LocalDataSource
import com.arisurya.jetpackpro.canangbali.data.source.local.room.CanangDatabase
import com.arisurya.jetpackpro.canangbali.data.source.remote.RemoteDataSource
import com.arisurya.jetpackpro.canangbali.utils.AppExecutors
import com.arisurya.jetpackpro.canangbali.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context) : CanangRepository{
        val database = CanangDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.canangDao())
        val appExecutors = AppExecutors()

        return CanangRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}