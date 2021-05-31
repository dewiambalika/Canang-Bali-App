package com.arisurya.jetpackpro.canangbali.ui.information.canang

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arisurya.jetpackpro.canangbali.data.source.CanangRepository
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.CanangEntity
import com.arisurya.jetpackpro.canangbali.utils.DataDummy

class CanangViewModel(private val canangRepository: CanangRepository): ViewModel(){
    fun getCanang() : LiveData<List<CanangEntity>> = canangRepository.getCanang()
}