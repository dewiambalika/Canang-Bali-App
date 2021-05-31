package com.arisurya.jetpackpro.canangbali.data.source

import androidx.lifecycle.LiveData
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.CanangEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.PhilosophyEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.ShopEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.UpakaraEntity

interface CanangDataSource {
    fun getCanang():LiveData<List<CanangEntity>>
    fun getUpakara(): LiveData<List<UpakaraEntity>>
    fun getShop(): LiveData<List<ShopEntity>>
    fun getPhilosophy(): LiveData<PhilosophyEntity>
    fun getDetailCanang(canangId : String) : LiveData<CanangEntity>
    fun getDetailUpakara(upakaraId : String) : LiveData<UpakaraEntity>
    fun getDetailShop(shopId : String): LiveData<ShopEntity>
}