package com.arisurya.jetpackpro.canangbali.ui.information.upakara.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arisurya.jetpackpro.canangbali.data.source.CanangRepository
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.UpakaraEntity
import com.arisurya.jetpackpro.canangbali.utils.DataDummy

class DetailUpakaraViewModel(private val canangRepository: CanangRepository) : ViewModel() {

    private lateinit var upakaraId: String

    fun setSelectedUpakara(upakaraId: String) {
        this.upakaraId = upakaraId
    }

    fun getDetailUpakara(): LiveData<UpakaraEntity> = canangRepository.getDetailUpakara(upakaraId)
}
