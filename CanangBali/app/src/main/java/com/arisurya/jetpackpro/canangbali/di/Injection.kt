package com.arisurya.jetpackpro.canangbali.di

import android.content.Context
import com.arisurya.jetpackpro.canangbali.data.source.CanangRepository
import com.arisurya.jetpackpro.canangbali.data.source.remote.RemoteDataSource
import com.arisurya.jetpackpro.canangbali.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context) : CanangRepository{
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        return CanangRepository.getInstance(remoteDataSource)
    }
}