package com.arisurya.jetpackpro.canangbali.ui.information

import androidx.lifecycle.ViewModel
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.CanangEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.ShopEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.UpakaraEntity
import com.arisurya.jetpackpro.canangbali.utils.DataDummy

class InformationViewModel : ViewModel() {

    fun getUpakara(): List<UpakaraEntity> = DataDummy.generateDummyUpakara()

    fun getCanang(): List<CanangEntity> = DataDummy.generateDummyCanang()

    fun getShop(): List<ShopEntity> = DataDummy.generateDummyShop()
}