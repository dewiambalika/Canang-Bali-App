package com.arisurya.jetpackpro.canangbali.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.CanangEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.PhilosophyEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.ShopEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.UpakaraEntity
import com.arisurya.jetpackpro.canangbali.data.source.remote.RemoteDataSource
import com.arisurya.jetpackpro.canangbali.data.source.remote.response.CanangResponse
import com.arisurya.jetpackpro.canangbali.data.source.remote.response.PhilosophyResponse
import com.arisurya.jetpackpro.canangbali.data.source.remote.response.ShopResponse
import com.arisurya.jetpackpro.canangbali.data.source.remote.response.UpakaraResponse


class CanangRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    CanangDataSource {

    companion object {
        @Volatile
        private var instance: CanangRepository? = null
        fun getInstance(remoteData: RemoteDataSource): CanangRepository =
            instance ?: synchronized(this) {
                instance ?: CanangRepository(remoteData).apply { instance = this }
            }
    }

    override fun getCanang(): LiveData<List<CanangEntity>> {
        val canangResults = MutableLiveData<List<CanangEntity>>()
        remoteDataSource.getCanang(object : RemoteDataSource.LoadCanangCallback{
            override fun onAllCanangReceived(canangResponse: List<CanangResponse>) {
                val canangList = ArrayList<CanangEntity>()
                for (response in canangResponse) {
                    val canang = CanangEntity(
                        response.canangId,
                        response.title,
                        response.imgPath,
                        response.function,
                        response.make
                    )
                    canangList.add(canang)
                }
                canangResults.postValue(canangList)
            }

        })
        return canangResults
    }

    override fun getUpakara(): LiveData<List<UpakaraEntity>> {
        val upakaraResults = MutableLiveData<List<UpakaraEntity>>()
        remoteDataSource.getUpakara(object : RemoteDataSource.LoadUpakaraCallback{
            override fun onAllUpakaraReceived(upakaraResponse: List<UpakaraResponse>) {
                val upakaraList = ArrayList<UpakaraEntity>()
                for (response in upakaraResponse) {
                    val upakara = UpakaraEntity(
                        response.upakaraId,
                        response.title,
                        response.imgPath,
                        response.desc,
                    )
                    upakaraList.add(upakara)
                }
                upakaraResults.postValue(upakaraList)
            }

        })

        return upakaraResults
    }

    override fun getShop(): LiveData<List<ShopEntity>> {
        val shopResults = MutableLiveData<List<ShopEntity>>()
        remoteDataSource.getShop(object : RemoteDataSource.LoadShopCallback{
            override fun onAllShopReceived(shopResponse: List<ShopResponse>) {
                val shopList = ArrayList<ShopEntity>()
                for (response in shopResponse) {
                    val shop = ShopEntity(
                        response.shopId,
                        response.name,
                        response.imgPath,
                        response.location,
                        response.tlp,
                        response.product,
                        response.desc
                    )
                    shopList.add(shop)
                }
                shopResults.postValue(shopList)
            }
        })

        return shopResults
    }

    override fun getPhilosophy(): LiveData<PhilosophyEntity> {
        val philosophyResults = MutableLiveData<PhilosophyEntity>()
        remoteDataSource.getPhilosophy(object : RemoteDataSource.LoadPhilosophyCallback {
            override fun onAllPhilosophyReceived(philosophyResponse: List<PhilosophyResponse>) {
                val philosophyList = ArrayList<PhilosophyEntity>()
                for (response in philosophyResponse) {
                    val philosophy = PhilosophyEntity(
                        response.philosophyId,
                        response.title,
                        response.imgPath,
                        response.desc
                    )
                    philosophyList.add(philosophy)
                }
                philosophyResults.postValue(philosophyList[0])
            }

        })

        return philosophyResults
    }

    override fun getDetailCanang(canangId: String): LiveData<CanangEntity> {
        val canangResult = MutableLiveData<CanangEntity>()

        remoteDataSource.getCanang(object : RemoteDataSource.LoadCanangCallback {
            override fun onAllCanangReceived(canangResponse: List<CanangResponse>) {
                lateinit var canang: CanangEntity
                for (response in canangResponse) {
                    if (canangId == response.canangId.toString()) {
                        canang = CanangEntity(
                            response.canangId,
                            response.title,
                            response.imgPath,
                            response.function,
                            response.make
                        )
                    }
                }
                canangResult.postValue(canang)
            }
        })
        return canangResult
    }

    override fun getDetailUpakara(upakaraId: String): LiveData<UpakaraEntity> {
        val upakaraResult = MutableLiveData<UpakaraEntity>()
        remoteDataSource.getUpakara(object : RemoteDataSource.LoadUpakaraCallback {
            override fun onAllUpakaraReceived(upakaraResponse: List<UpakaraResponse>) {
                lateinit var upakara: UpakaraEntity
                for (response in upakaraResponse) {
                    if (upakaraId == response.upakaraId.toString()) {
                        upakara = UpakaraEntity(
                            response.upakaraId,
                            response.title,
                            response.imgPath,
                            response.desc,
                        )
                    }
                }
                upakaraResult.postValue(upakara)
            }
        })
        return upakaraResult
    }

    override fun getDetailShop(shopId: String): LiveData<ShopEntity> {

        val shopResult = MutableLiveData<ShopEntity>()
        remoteDataSource.getShop(object : RemoteDataSource.LoadShopCallback {
            override fun onAllShopReceived(shopResponse: List<ShopResponse>) {
                lateinit var shop: ShopEntity
                for (response in shopResponse) {
                    if (shopId == response.shopId.toString()) {
                        shop = ShopEntity(
                            response.shopId,
                            response.name,
                            response.imgPath,
                            response.location,
                            response.tlp,
                            response.product,
                            response.desc
                        )
                    }
                }
                shopResult.postValue(shop)
            }
        })

        return shopResult
    }
}