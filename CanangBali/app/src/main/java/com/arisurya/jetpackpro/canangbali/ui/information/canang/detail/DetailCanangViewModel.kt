package com.arisurya.jetpackpro.canangbali.ui.information.canang.detail

import androidx.lifecycle.ViewModel
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.CanangEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.UpakaraEntity
import com.arisurya.jetpackpro.canangbali.utils.DataDummy

class DetailCanangViewModel : ViewModel() {

    private lateinit  var  canangId : String

    fun setSelectedCanang(canangId : String){
        this.canangId = canangId
    }

    fun getDetailCanang() : CanangEntity {
        lateinit var canang : CanangEntity
        val canangEntities = DataDummy.generateDummyCanang()
        for(canangEntity in canangEntities){
            if(canangEntity.canangId == canangId.toInt()){
                canang = canangEntity
            }
        }

        return  canang
    }
}