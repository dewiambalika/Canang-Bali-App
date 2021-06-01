package com.arisurya.jetpackpro.canangbali.ui.information.philosophy


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arisurya.jetpackpro.canangbali.data.source.CanangRepository
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.PhilosophyEntity
import com.arisurya.jetpackpro.canangbali.utils.DataDummy
import com.arisurya.jetpackpro.canangbali.vo.Resource

class PhilosophyViewModel(private val canangRepository: CanangRepository) : ViewModel() {
    fun getPhilosophy() : LiveData<Resource<List<PhilosophyEntity>>> = canangRepository.getPhilosophy()
}