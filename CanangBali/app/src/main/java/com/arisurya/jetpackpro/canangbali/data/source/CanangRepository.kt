package com.arisurya.jetpackpro.canangbali.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arisurya.jetpackpro.canangbali.data.source.local.LocalDataSource
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.CanangEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.PhilosophyEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.ShopEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.UpakaraEntity
import com.arisurya.jetpackpro.canangbali.data.source.remote.ApiResponse
import com.arisurya.jetpackpro.canangbali.data.source.remote.RemoteDataSource
import com.arisurya.jetpackpro.canangbali.data.source.remote.response.CanangResponse
import com.arisurya.jetpackpro.canangbali.data.source.remote.response.PhilosophyResponse
import com.arisurya.jetpackpro.canangbali.data.source.remote.response.ShopResponse
import com.arisurya.jetpackpro.canangbali.data.source.remote.response.UpakaraResponse
import com.arisurya.jetpackpro.canangbali.ui.scan.UploadRequestBody
import com.arisurya.jetpackpro.canangbali.utils.AppExecutors
import com.arisurya.jetpackpro.canangbali.vo.Resource
import java.io.File


class CanangRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    CanangDataSource {

    companion object {
        @Volatile
        private var instance: CanangRepository? = null
        fun getInstance(remoteData: RemoteDataSource, localData: LocalDataSource, appExecutors: AppExecutors): CanangRepository =
            instance ?: synchronized(this) {
                instance ?: CanangRepository(remoteData, localData, appExecutors).apply { instance = this }
            }
    }

    override fun getCanang(): LiveData<Resource<List<CanangEntity>>> {

        return object : NetworkBoundResource<List<CanangEntity>, List<CanangResponse>>(appExecutors){
            override fun loadFromDB(): LiveData<List<CanangEntity>> =
                localDataSource.getCanang()

            override fun shouldFetch(data: List<CanangEntity>?): Boolean =
                data ==null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<CanangResponse>>> =
                remoteDataSource.getCanang()

            override fun saveCallResult(canangResponse: List<CanangResponse>) {
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
                localDataSource.insertCanang(canangList)
            }
        }.asLiveData()
    }

    override fun getUpakara(): LiveData<Resource<List<UpakaraEntity>>> {

        return object : NetworkBoundResource<List<UpakaraEntity>, List<UpakaraResponse>>(appExecutors){
            override fun loadFromDB(): LiveData<List<UpakaraEntity>> =
                localDataSource.getUpakara()

            override fun shouldFetch(data: List<UpakaraEntity>?): Boolean =
                data ==null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<UpakaraResponse>>> =
                remoteDataSource.getUpakara()

            override fun saveCallResult(data: List<UpakaraResponse>) {
                val upakaraList = ArrayList<UpakaraEntity>()
                for (response in data) {
                    val upakara = UpakaraEntity(
                        response.upakaraId,
                        response.title,
                        response.imgPath,
                        response.desc,
                    )
                    upakaraList.add(upakara)
                }
                localDataSource.insertUpakara(upakaraList)
            }
        }.asLiveData()
    }

    override fun getShop(): LiveData<Resource<List<ShopEntity>>> {

        return object : NetworkBoundResource<List<ShopEntity>, List<ShopResponse>>(appExecutors){
            override fun loadFromDB(): LiveData<List<ShopEntity>> =
                localDataSource.getShop()
            override fun shouldFetch(data: List<ShopEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ShopResponse>>> =
                remoteDataSource.getShop()

            override fun saveCallResult(data: List<ShopResponse>) {
                val shopList = ArrayList<ShopEntity>()
                for (response in data) {
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
                localDataSource.insertShop(shopList)
            }
        }.asLiveData()
    }

    override fun getPhilosophy(): LiveData<Resource<List<PhilosophyEntity>>> {

        return object : NetworkBoundResource<List<PhilosophyEntity>, List<PhilosophyResponse>>(appExecutors){
            override fun loadFromDB(): LiveData<List<PhilosophyEntity>> =
                localDataSource.getPhilosophy()
            override fun shouldFetch(data: List<PhilosophyEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<PhilosophyResponse>>> =
                 remoteDataSource.getPhilosophy()

            override fun saveCallResult(data: List<PhilosophyResponse>) {
                val philosophyList = ArrayList<PhilosophyEntity>()
                for (response in data) {
                    val philosophy = PhilosophyEntity(
                        response.philosophyId,
                        response.title,
                        response.imgPath,
                        response.desc
                    )
                    philosophyList.add(philosophy)
                }
                localDataSource.insertPhilosophy(philosophyList)
            }
        }.asLiveData()
    }

    override fun getDetailCanang(canangId: String): LiveData<Resource<CanangEntity>> {
        return object : NetworkBoundResource<CanangEntity, CanangResponse>(appExecutors){
            override fun loadFromDB(): LiveData<CanangEntity> =
                localDataSource.getDetailCanang(canangId)

            override fun shouldFetch(data: CanangEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<CanangResponse>> =
                remoteDataSource.getDetailCanang(canangId.toInt())

            override fun saveCallResult(data: CanangResponse) {
                val canang = CanangEntity(
                    data.canangId,
                    data.title,
                    data.imgPath,
                    data.function,
                    data.make
                )
                localDataSource.updateDetailCanang(canang)
            }
        }.asLiveData()
    }

    override fun getDetailUpakara(upakaraId: String): LiveData<Resource<UpakaraEntity>> {

        return object : NetworkBoundResource<UpakaraEntity, UpakaraResponse>(appExecutors){
            override fun loadFromDB(): LiveData<UpakaraEntity> =
                localDataSource.getDetailUpakara(upakaraId)

            override fun shouldFetch(data: UpakaraEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<UpakaraResponse>> =
                remoteDataSource.getDetailUpakara(upakaraId.toInt())

            override fun saveCallResult(data: UpakaraResponse) {
                val upakara = UpakaraEntity(
                    data.upakaraId,
                    data.title,
                    data.imgPath,
                    data.desc
                )

                localDataSource.updateDetailUpakara(upakara)
            }

        }.asLiveData()
    }

    override fun getDetailShop(shopId: String): LiveData<Resource<ShopEntity>> {
        return object : NetworkBoundResource<ShopEntity, ShopResponse>(appExecutors){
            override fun loadFromDB(): LiveData<ShopEntity> =
                localDataSource.getDetailShop(shopId)

            override fun shouldFetch(data: ShopEntity?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<ShopResponse>> =
                remoteDataSource.getDetailShop(shopId.toInt())

            override fun saveCallResult(data: ShopResponse) {
                val shop = ShopEntity(
                    data.shopId,
                    data.name,
                    data.imgPath,
                    data.location,
                    data.tlp,
                    data.product,
                    data.desc
                )
                localDataSource.updateDetailShop(shop)
            }

        }.asLiveData()
    }

    override fun getBookmarkCanang(): LiveData<List<CanangEntity>> =
        localDataSource.getBookmarkedCanang()

    override fun getBookmarkUpakara(): LiveData<List<UpakaraEntity>> =
        localDataSource.getBookmarkedUpakara()

    override fun getBookmarkShop(): LiveData<List<ShopEntity>> =
        localDataSource.getBookmarkedShop()

    override fun setCanangBookmark(canang: CanangEntity, newState: Boolean) =
        appExecutors.diskIO().execute{localDataSource.setCanangBookmark(canang,newState)}

    override fun setUpakaraBookmark(upakara: UpakaraEntity, newState: Boolean) =
        appExecutors.diskIO().execute{localDataSource.setUpakaraBookmark(upakara,newState)}

    override fun setShopBookmark(shop: ShopEntity, newState: Boolean) =
        appExecutors.diskIO().execute{localDataSource.setShopBookmark(shop,newState)}

    override fun getCountBookmarkCanang(): LiveData<Int> =
        localDataSource.getCountBookmarkCanang()

    override fun getCountBookmarkUpakara(): LiveData<Int> =
        localDataSource.getCountBookmarkUpakara()

    override fun getCountBookmarkShop(): LiveData<Int> =
        localDataSource.getCountBookmarkShop()

    override fun getResultDetection(
        body: UploadRequestBody,
        file: File,
        contentType: String
    ): LiveData<String> {
        lateinit var  result : LiveData<String>
        result = remoteDataSource.uploadPhotoPredict(body,file,contentType)
        return result
    }

}