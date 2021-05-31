package com.arisurya.jetpackpro.canangbali.ui.information.shop.detail

import androidx.lifecycle.ViewModel
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.CanangEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.ShopEntity
import com.arisurya.jetpackpro.canangbali.utils.DataDummy

class DetailShopViewModel: ViewModel() {

    private lateinit  var  shopId : String

    fun setSelectedShop(shopId : String){
        this.shopId = shopId
    }

    fun getDetailShop() : ShopEntity {
        lateinit var shop : ShopEntity
        val shopEntities = DataDummy.generateDummyShop()
        for(shopEntity in shopEntities){
            if(shopEntity.shopId == shopId.toInt()){
                shop = shopEntity
            }
        }
        return shop
    }
}