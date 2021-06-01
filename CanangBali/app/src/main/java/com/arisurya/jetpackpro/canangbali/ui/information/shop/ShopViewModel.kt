package com.arisurya.jetpackpro.canangbali.ui.information.shop


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arisurya.jetpackpro.canangbali.data.source.CanangRepository
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.ShopEntity
import com.arisurya.jetpackpro.canangbali.utils.DataDummy
import com.arisurya.jetpackpro.canangbali.vo.Resource


class ShopViewModel(private val canangRepository: CanangRepository) : ViewModel() {
    fun getShop():LiveData<Resource<List<ShopEntity>>> = canangRepository.getShop()
}