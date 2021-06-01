package com.arisurya.jetpackpro.canangbali.ui.information.upakara.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.arisurya.jetpackpro.canangbali.data.source.CanangRepository
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.UpakaraEntity
import com.arisurya.jetpackpro.canangbali.utils.DataDummy
import com.arisurya.jetpackpro.canangbali.vo.Resource

class DetailUpakaraViewModel(private val canangRepository: CanangRepository) : ViewModel() {

    var upakaraId = MutableLiveData<String>()

    fun setSelectedUpakara(upakaraId: String) {
        this.upakaraId.value = upakaraId
    }

    var detailUpakara: LiveData<Resource<UpakaraEntity>> =
        Transformations.switchMap(upakaraId) { mUpakaraId ->
            canangRepository.getDetailUpakara(mUpakaraId)
        }

    fun setBookmarkUpakara() {
        val upakaraResource = detailUpakara.value
        if (upakaraResource != null) {
            val upakara = upakaraResource.data
            if (upakara != null) {
                val upakaraEntity = upakara
                val newState = !upakara.bookmarked
                canangRepository.setUpakaraBookmark(upakaraEntity, newState)
            }
        }
    }
}
