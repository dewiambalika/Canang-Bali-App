package com.arisurya.jetpackpro.canangbali.ui.information.shop.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arisurya.jetpackpro.canangbali.data.source.CanangRepository
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.CanangEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.ShopEntity
import com.arisurya.jetpackpro.canangbali.utils.DataDummy

class DetailShopViewModel(private val canangRepository: CanangRepository): ViewModel() {

    private lateinit  var  shopId : String

    fun setSelectedShop(shopId : String){
        this.shopId = shopId
    }

    fun getDetailShop() : LiveData<ShopEntity> = canangRepository.getDetailShop(shopId)
}