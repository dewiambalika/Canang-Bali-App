package com.arisurya.jetpackpro.canangbali.ui.information.shop.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.arisurya.jetpackpro.canangbali.data.source.CanangRepository
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.CanangEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.ShopEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.UpakaraEntity
import com.arisurya.jetpackpro.canangbali.utils.DataDummy
import com.arisurya.jetpackpro.canangbali.vo.Resource

class DetailShopViewModel(private val canangRepository: CanangRepository): ViewModel() {

    var  shopId = MutableLiveData<String>()

    fun setSelectedShop(shopId : String){
        this.shopId.value = shopId
    }

    var detailShop: LiveData<Resource<ShopEntity>> =
        Transformations.switchMap(shopId) { mShopId ->
            canangRepository.getDetailShop(mShopId)
        }

    fun setBookmarkShop() {
        val shopResource = detailShop.value
        if (shopResource != null) {
            val shop = shopResource.data
            if (shop != null) {
                val shopEntity = shop
                val newState = !shop.bookmarked
                canangRepository.setShopBookmark(shopEntity, newState)
            }
        }
    }
}