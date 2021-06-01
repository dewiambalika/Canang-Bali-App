package com.arisurya.jetpackpro.canangbali.ui.dashboard.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arisurya.jetpackpro.canangbali.data.source.CanangRepository
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.CanangEntity

class BookmarkCanangViewModel(private val canangRepository: CanangRepository) : ViewModel() {
    fun getBookmarkCanang():LiveData<List<CanangEntity>> = canangRepository.getBookmarkCanang()
}