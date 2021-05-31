package com.arisurya.jetpackpro.canangbali.ui.information.upakara.detail

import androidx.lifecycle.ViewModel
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.UpakaraEntity
import com.arisurya.jetpackpro.canangbali.utils.DataDummy

class DetailUpakaraViewModel : ViewModel() {

    private lateinit  var  upakaraId : String

    fun setSelectedUpakara(upakaraId : String){
        this.upakaraId = upakaraId
    }

    fun getDetailUpakara() : UpakaraEntity{
        lateinit var upakara : UpakaraEntity
        val upakaraEntities = DataDummy.generateDummyUpakara()
        for(upakaraEntity in upakaraEntities){
            if(upakaraEntity.upakaraId == upakaraId.toInt()){
                upakara = upakaraEntity
            }
        }

        return  upakara
    }
}