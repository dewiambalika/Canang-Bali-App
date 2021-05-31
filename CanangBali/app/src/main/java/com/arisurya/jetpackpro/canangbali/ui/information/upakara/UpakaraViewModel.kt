package com.arisurya.jetpackpro.canangbali.ui.information.upakara

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arisurya.jetpackpro.canangbali.data.source.CanangRepository
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.UpakaraEntity
import com.arisurya.jetpackpro.canangbali.utils.DataDummy

class UpakaraViewModel(private val canangRepository: CanangRepository) : ViewModel() {

    fun getUpakara(): LiveData<List<UpakaraEntity>> = canangRepository.getUpakara()
}