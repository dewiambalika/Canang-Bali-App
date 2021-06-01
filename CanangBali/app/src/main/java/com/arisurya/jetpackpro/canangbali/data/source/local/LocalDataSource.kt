package com.arisurya.jetpackpro.canangbali.data.source.local

import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LiveData
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.CanangEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.PhilosophyEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.ShopEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.UpakaraEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.room.CanangDao

class LocalDataSource private constructor(private val mCanangDao: CanangDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(canangDao: CanangDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(canangDao)
    }

    fun getCanang(): LiveData<List<CanangEntity>> = mCanangDao.getCanang()

    fun getUpakara(): LiveData<List<UpakaraEntity>> = mCanangDao.getUpakara()

    fun getShop(): LiveData<List<ShopEntity>> = mCanangDao.getShop()

    fun getPhilosophy(): LiveData<List<PhilosophyEntity>> = mCanangDao.getPhilosophy()

    fun getDetailCanang(canangId: String): LiveData<CanangEntity> =
        mCanangDao.getDetailCanang(canangId)

    fun getDetailUpakara(upakaraId: String): LiveData<UpakaraEntity> =
        mCanangDao.getDetailUpakara(upakaraId)

    fun getDetailShop(shopId: String): LiveData<ShopEntity> = mCanangDao.getDetailShop(shopId)

    fun getBookmarkedCanang(): LiveData<List<CanangEntity>> = mCanangDao.getBookmarkedCanang()

    fun getBookmarkedUpakara(): LiveData<List<UpakaraEntity>> = mCanangDao.getBookmarkedUpakara()

    fun getBookmarkedShop(): LiveData<List<ShopEntity>> = mCanangDao.getBookmarkedShop()

    fun insertCanang(canang: List<CanangEntity>) = mCanangDao.insertCanang(canang)

    fun insertUpakara(upakara: List<UpakaraEntity>) = mCanangDao.insertUpakara(upakara)

    fun insertShop(shop: List<ShopEntity>) = mCanangDao.insertShop(shop)

    fun insertPhilosophy(philosophy: List<PhilosophyEntity>) =
        mCanangDao.insertPhilosophy(philosophy)

    fun setCanangBookmark(canang: CanangEntity, newState: Boolean) {
        canang.bookmarked = newState
        mCanangDao.updateCanang(canang)
    }

    fun setUpakaraBookmark(upakara: UpakaraEntity, newState: Boolean) {
        upakara.bookmarked = newState
        mCanangDao.updateUpakara(upakara)
    }

    fun setShopBookmark(shop: ShopEntity, newState: Boolean) {
        shop.bookmarked = newState
        mCanangDao.updateShope(shop)
    }

    fun updateDetailCanang(canang : CanangEntity){
         mCanangDao.updateCanang(canang)
    }

    fun updateDetailUpakara(upakara : UpakaraEntity){
        mCanangDao.updateUpakara(upakara)
    }

    fun updateDetailShop(shop : ShopEntity){
        mCanangDao.updateShope(shop)
    }
}