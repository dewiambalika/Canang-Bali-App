package com.arisurya.jetpackpro.canangbali.data.source.remote

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.CanangEntity
import com.arisurya.jetpackpro.canangbali.data.source.remote.response.CanangResponse
import com.arisurya.jetpackpro.canangbali.data.source.remote.response.PhilosophyResponse
import com.arisurya.jetpackpro.canangbali.data.source.remote.response.ShopResponse
import com.arisurya.jetpackpro.canangbali.data.source.remote.response.UpakaraResponse
import com.arisurya.jetpackpro.canangbali.utils.JsonHelper


class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper).apply { instance = this }
            }
    }

    fun getCanang(): LiveData<ApiResponse<List<CanangResponse>>> {
        val resultCanang = MutableLiveData<ApiResponse<List<CanangResponse>>>()
        handler.postDelayed(
            {
                resultCanang.value = ApiResponse.success(jsonHelper.loadCanang())
            },
            SERVICE_LATENCY_IN_MILLIS
        )
        return  resultCanang
    }

    fun getDetailCanang(canangiId : Int): LiveData<ApiResponse<CanangResponse>> {
        val resultCanang = MutableLiveData<ApiResponse<CanangResponse>>()
        handler.postDelayed(
            {
                resultCanang.value = ApiResponse.success(jsonHelper.loadCanang()[canangiId])
            },
            SERVICE_LATENCY_IN_MILLIS
        )
        return  resultCanang
    }

    fun getUpakara() : LiveData<ApiResponse<List<UpakaraResponse>>> {
        val resultUpakara = MutableLiveData<ApiResponse<List<UpakaraResponse>>>()
        handler.postDelayed(
            {
                resultUpakara.value = ApiResponse.success(jsonHelper.loadUpakara())
            },
            SERVICE_LATENCY_IN_MILLIS
        )
        return resultUpakara
    }

    fun getDetailUpakara(upakaraId : Int) : LiveData<ApiResponse<UpakaraResponse>> {
        val resultUpakara = MutableLiveData<ApiResponse<UpakaraResponse>>()
        handler.postDelayed(
            {
                resultUpakara.value = ApiResponse.success(jsonHelper.loadUpakara()[upakaraId])
            },
            SERVICE_LATENCY_IN_MILLIS
        )
        return resultUpakara
    }

    fun getShop() : LiveData<ApiResponse<List<ShopResponse>>> {
        val resultShop = MutableLiveData<ApiResponse<List<ShopResponse>>>()
        handler.postDelayed(
            {
                resultShop.value = ApiResponse.success(jsonHelper.loadShop())
            },
            SERVICE_LATENCY_IN_MILLIS
        )
        return resultShop
    }

    fun getDetailShop(shopId : Int) : LiveData<ApiResponse<ShopResponse>> {
        val resultShop = MutableLiveData<ApiResponse<ShopResponse>>()
        handler.postDelayed(
            {
                resultShop.value = ApiResponse.success(jsonHelper.loadShop()[shopId])
            },
            SERVICE_LATENCY_IN_MILLIS
        )
        return resultShop
    }

    fun getPhilosophy() : LiveData<ApiResponse<List<PhilosophyResponse>>> {
        val resultPhilosophy = MutableLiveData<ApiResponse<List<PhilosophyResponse>>>()
        handler.postDelayed(
            { resultPhilosophy.value = ApiResponse.success(jsonHelper.loadPhilosophy())},
            SERVICE_LATENCY_IN_MILLIS
        )
        return resultPhilosophy
    }

    interface LoadCanangCallback {
        fun onAllCanangReceived(canangResponse: List<CanangResponse>)
    }

    interface LoadUpakaraCallback {
        fun onAllUpakaraReceived(upakaraResponse: List<UpakaraResponse>)
    }

    interface LoadShopCallback {
        fun onAllShopReceived(shopResponse: List<ShopResponse>)
    }

    interface LoadPhilosophyCallback {
        fun onAllPhilosophyReceived(philosophyResponse: List<PhilosophyResponse>)
    }
}