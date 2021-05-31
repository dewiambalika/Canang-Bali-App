package com.arisurya.jetpackpro.canangbali.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arisurya.jetpackpro.canangbali.data.source.CanangRepository
import com.arisurya.jetpackpro.canangbali.di.Injection
import com.arisurya.jetpackpro.canangbali.ui.information.InformationViewModel
import com.arisurya.jetpackpro.canangbali.ui.information.canang.CanangViewModel
import com.arisurya.jetpackpro.canangbali.ui.information.canang.detail.DetailCanangViewModel
import com.arisurya.jetpackpro.canangbali.ui.information.philosophy.PhilosophyViewModel
import com.arisurya.jetpackpro.canangbali.ui.information.shop.ShopViewModel
import com.arisurya.jetpackpro.canangbali.ui.information.shop.detail.DetailShopViewModel
import com.arisurya.jetpackpro.canangbali.ui.information.upakara.UpakaraViewModel
import com.arisurya.jetpackpro.canangbali.ui.information.upakara.detail.DetailUpakaraViewModel

class ViewModelFactory private constructor(private val mCanangRepository: CanangRepository) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(InformationViewModel::class.java) -> {
                return InformationViewModel(mCanangRepository) as T
            }
            modelClass.isAssignableFrom(CanangViewModel::class.java) -> {
                return CanangViewModel(mCanangRepository) as T
            }
            modelClass.isAssignableFrom(DetailCanangViewModel::class.java) -> {
                return DetailCanangViewModel(mCanangRepository) as T
            }
            modelClass.isAssignableFrom(UpakaraViewModel::class.java) -> {
                return UpakaraViewModel(mCanangRepository) as T
            }
            modelClass.isAssignableFrom(DetailUpakaraViewModel::class.java) -> {
                return DetailUpakaraViewModel(mCanangRepository) as T
            }
            modelClass.isAssignableFrom(ShopViewModel::class.java) -> {
                return ShopViewModel(mCanangRepository) as T
            }
            modelClass.isAssignableFrom(DetailShopViewModel::class.java) -> {
                return DetailShopViewModel(mCanangRepository) as T
            }
            modelClass.isAssignableFrom(PhilosophyViewModel::class.java) -> {
                return PhilosophyViewModel(mCanangRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}