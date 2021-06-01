package com.arisurya.jetpackpro.canangbali.ui.information.canang

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arisurya.jetpackpro.canangbali.data.source.CanangRepository
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.CanangEntity
import com.arisurya.jetpackpro.canangbali.utils.DataDummy
import com.arisurya.jetpackpro.canangbali.vo.Resource


class CanangViewModel(private val canangRepository: CanangRepository): ViewModel(){
    fun getCanang() : LiveData<Resource<List<CanangEntity>>> = canangRepository.getCanang()
}