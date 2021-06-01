package com.arisurya.jetpackpro.canangbali.data.source

import androidx.lifecycle.LiveData
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.CanangEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.PhilosophyEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.ShopEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.UpakaraEntity
import com.arisurya.jetpackpro.canangbali.vo.Resource

interface CanangDataSource {
    fun getCanang():LiveData<Resource<List<CanangEntity>>>
    fun getUpakara(): LiveData<Resource<List<UpakaraEntity>>>
    fun getShop(): LiveData<Resource<List<ShopEntity>>>
    fun getPhilosophy(): LiveData<Resource<List<PhilosophyEntity>>>
    fun getDetailCanang(canangId : String) : LiveData<Resource<CanangEntity>>
    fun getDetailUpakara(upakaraId : String) : LiveData<Resource<UpakaraEntity>>
    fun getDetailShop(shopId : String): LiveData<Resource<ShopEntity>>
    fun getBookmarkCanang(): LiveData<List<CanangEntity>>
    fun getBookmarkUpakara(): LiveData<List<UpakaraEntity>>
    fun getBookmarkShop(): LiveData<List<ShopEntity>>
    fun setCanangBookmark(canang : CanangEntity, newState: Boolean)
    fun setUpakaraBookmark(upakara : UpakaraEntity, newState: Boolean)
    fun setShopBookmark(shop:ShopEntity, newState: Boolean)
}