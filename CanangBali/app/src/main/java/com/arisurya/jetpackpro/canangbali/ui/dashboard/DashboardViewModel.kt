package com.arisurya.jetpackpro.canangbali.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arisurya.jetpackpro.canangbali.data.source.CanangRepository

class DashboardViewModel(private val canangRepository: CanangRepository) : ViewModel() {
    fun getCountBookmarkCanang(): LiveData<Int> = canangRepository.getCountBookmarkCanang()

    fun getCountBookmarkUpakara(): LiveData<Int> = canangRepository.getCountBookmarkUpakara()

    fun getCountBookmarkShop(): LiveData<Int> = canangRepository.getCountBookmarkShop()
}