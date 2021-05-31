package com.arisurya.jetpackpro.canangbali.ui.information.canang.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arisurya.jetpackpro.canangbali.data.source.CanangRepository
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.CanangEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.UpakaraEntity
import com.arisurya.jetpackpro.canangbali.utils.DataDummy

class DetailCanangViewModel(private val canangRepository: CanangRepository) : ViewModel() {

    private lateinit  var  canangId : String

    fun setSelectedCanang(canangId : String){
        this.canangId = canangId
    }

    fun getDetailCanang() : LiveData<CanangEntity> = canangRepository.getDetailCanang(canangId)
}