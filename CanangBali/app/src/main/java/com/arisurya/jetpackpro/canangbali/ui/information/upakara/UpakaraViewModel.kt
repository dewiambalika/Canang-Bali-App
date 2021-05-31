package com.arisurya.jetpackpro.canangbali.ui.information.upakara

import androidx.lifecycle.ViewModel
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.UpakaraEntity
import com.arisurya.jetpackpro.canangbali.utils.DataDummy

class UpakaraViewModel : ViewModel() {

    fun getUpakara():List<UpakaraEntity> = DataDummy.generateDummyUpakara()
}