package com.arisurya.jetpackpro.canangbali.ui.dashboard.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arisurya.jetpackpro.canangbali.data.source.CanangRepository
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.UpakaraEntity

class BookmarkUpakaraViewModel (private val canangRepository: CanangRepository) : ViewModel() {
    fun getBookmarkUpakara(): LiveData<List<UpakaraEntity>> = canangRepository.getBookmarkUpakara()
}