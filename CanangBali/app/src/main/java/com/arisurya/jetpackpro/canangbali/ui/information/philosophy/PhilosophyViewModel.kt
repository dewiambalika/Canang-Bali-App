package com.arisurya.jetpackpro.canangbali.ui.information.philosophy


import androidx.lifecycle.ViewModel
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.PhilosophyEntity
import com.arisurya.jetpackpro.canangbali.utils.DataDummy

class PhilosophyViewModel : ViewModel() {
    fun getPhilosophy() : PhilosophyEntity = DataDummy.generateDummyPhilosophy()
}