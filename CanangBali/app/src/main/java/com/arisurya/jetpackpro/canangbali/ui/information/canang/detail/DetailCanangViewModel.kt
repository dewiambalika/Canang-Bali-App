package com.arisurya.jetpackpro.canangbali.ui.information.canang.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.arisurya.jetpackpro.canangbali.data.source.CanangRepository
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.CanangEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.ShopEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.UpakaraEntity
import com.arisurya.jetpackpro.canangbali.utils.DataDummy
import com.arisurya.jetpackpro.canangbali.vo.Resource

class DetailCanangViewModel(private val canangRepository: CanangRepository) : ViewModel() {

    var  canangId = MutableLiveData<String>()

    fun setSelectedCanang(canangId : String){
        this.canangId.value = canangId
    }

    var detailCanang: LiveData<Resource<CanangEntity>> =
        Transformations.switchMap(canangId) { mCanangId ->
            canangRepository.getDetailCanang(mCanangId)
        }

    fun setBookmarkCanang() {
        val canangResource = detailCanang.value
        if (canangResource != null) {
            val canang = canangResource.data
            if (canang != null) {
                val canangEntity = canang
                val newState = !canang.bookmarked
                canangRepository.setCanangBookmark(canangEntity, newState)
            }
        }
    }
}