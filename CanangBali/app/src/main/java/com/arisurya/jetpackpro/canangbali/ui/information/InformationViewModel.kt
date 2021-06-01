package com.arisurya.jetpackpro.canangbali.ui.information

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arisurya.jetpackpro.canangbali.data.source.CanangRepository
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.CanangEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.ShopEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.UpakaraEntity
import com.arisurya.jetpackpro.canangbali.utils.DataDummy
import com.arisurya.jetpackpro.canangbali.vo.Resource

class InformationViewModel(private val canangRepository: CanangRepository) : ViewModel() {

    fun getUpakara(): LiveData<Resource<List<UpakaraEntity>>> = canangRepository.getUpakara()

    fun getCanang(): LiveData<Resource<List<CanangEntity>>> = canangRepository.getCanang()

    fun getShop(): LiveData<Resource<List<ShopEntity>>> = canangRepository.getShop()
}