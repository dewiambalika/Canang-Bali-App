package com.arisurya.jetpackpro.canangbali.ui.information.canang

import androidx.lifecycle.ViewModel
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.CanangEntity
import com.arisurya.jetpackpro.canangbali.utils.DataDummy

class CanangViewModel : ViewModel(){
    fun getCanang() : List<CanangEntity> = DataDummy.generateDummyCanang()
}