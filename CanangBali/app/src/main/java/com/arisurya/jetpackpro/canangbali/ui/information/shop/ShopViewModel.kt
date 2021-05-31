package com.arisurya.jetpackpro.canangbali.ui.information.shop


import androidx.lifecycle.ViewModel
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.ShopEntity
import com.arisurya.jetpackpro.canangbali.utils.DataDummy

class ShopViewModel : ViewModel() {
    fun getShop():List<ShopEntity> = DataDummy.generateDummyShop()
}