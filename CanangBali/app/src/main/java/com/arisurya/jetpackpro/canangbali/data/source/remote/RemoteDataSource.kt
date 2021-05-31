package com.arisurya.jetpackpro.canangbali.data.source.remote

import android.os.Handler
import android.os.Looper
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

    fun getCanang(callback: LoadCanangCallback) {
        handler.postDelayed(
            { callback.onAllCanangReceived(jsonHelper.loadCanang()) },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    fun getUpakara(callback: LoadUpakaraCallback) {
        handler.postDelayed(
            { callback.onAllUpakaraReceived(jsonHelper.loadUpakara()) },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    fun getShop(callback: LoadShopCallback) {
        handler.postDelayed(
            { callback.onAllShopReceived(jsonHelper.loadShop()) },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    fun getPhilosophy(callback: LoadPhilosophyCallback) {
        handler.postDelayed(
            { callback.onAllPhilosophyReceived(jsonHelper.loadPhilosophy()) },
            SERVICE_LATENCY_IN_MILLIS
        )
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