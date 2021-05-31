package com.arisurya.jetpackpro.canangbali.ui.information

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arisurya.jetpackpro.canangbali.data.source.CanangRepository
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.CanangEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.ShopEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.UpakaraEntity
import com.arisurya.jetpackpro.canangbali.utils.DataDummy

class InformationViewModel(private val canangRepository: CanangRepository) : ViewModel() {

    fun getUpakara(): LiveData<List<UpakaraEntity>> = canangRepository.getUpakara()

    fun getCanang(): LiveData<List<CanangEntity>> = canangRepository.getCanang()

    fun getShop(): LiveData<List<ShopEntity>> = canangRepository.getShop()
}