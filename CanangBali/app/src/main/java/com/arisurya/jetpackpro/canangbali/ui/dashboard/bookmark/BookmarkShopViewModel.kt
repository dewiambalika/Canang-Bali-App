package com.arisurya.jetpackpro.canangbali.ui.dashboard.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arisurya.jetpackpro.canangbali.data.source.CanangRepository
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.ShopEntity

class BookmarkShopViewModel (private val canangRepository: CanangRepository): ViewModel() {
    fun getBookmarkShop(): LiveData<List<ShopEntity>> = canangRepository.getBookmarkShop()
}