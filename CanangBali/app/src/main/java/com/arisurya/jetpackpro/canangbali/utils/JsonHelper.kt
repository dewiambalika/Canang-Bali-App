package com.arisurya.jetpackpro.canangbali.utils

import android.content.Context
import com.arisurya.jetpackpro.canangbali.data.source.remote.response.CanangResponse
import com.arisurya.jetpackpro.canangbali.data.source.remote.response.PhilosophyResponse
import com.arisurya.jetpackpro.canangbali.data.source.remote.response.ShopResponse
import com.arisurya.jetpackpro.canangbali.data.source.remote.response.UpakaraResponse
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadCanang(): List<CanangResponse> {
        val list = ArrayList<CanangResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("CanangResponses.json").toString())
            val listArray = responseObject.getJSONArray("canang")
            for (i in 0 until listArray.length()) {
                val canang = listArray.getJSONObject(i)

                val canangId = canang.getString("canangId")
                val title = canang.getString("title")
                val imgPath = canang.getString("imgPath")
                val function = canang.getString("function")
                val make = canang.getString("make")

                val canangResponse =
                    CanangResponse(canangId.toInt(), title, imgPath, function, make)
                list.add(canangResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

    fun loadCanangFromFirebase():List<CanangResponse>{
        val list = ArrayList<CanangResponse>()
        try {
            var firebase = FirebaseDatabase.getInstance().getReference("canang")

            firebase.addValueEventListener(object  : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        for (data in snapshot.children){
//                            var canangId  =data.child("canangId").getValue()
//                            var title = data.child("title").getValue()
//                            var imgPath = data.child("imgPath").getValue()
//                            var fungsi = data.child("function").getValue()
//                            var make = data.child("make").getValue()
//                            var canang = CanangResponse(
//                                canangId.toString().toInt(),
//                                title.toString(),
//                                imgPath.toString(),
//                                fungsi.toString(),
//                                make.toString()
//                            )
                            var canang = data.getValue(CanangResponse::class.java)!!
                            list.add(canang)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })


        }catch (e : Exception){
            e.printStackTrace()
        }
        return list
    }

    fun loadUpakara(): List<UpakaraResponse> {
        val list = ArrayList<UpakaraResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("UpakaraResponses.json").toString())
            val listArray = responseObject.getJSONArray("upakara")
            for (i in 0 until listArray.length()) {
                val upakara = listArray.getJSONObject(i)

                val upakaraId = upakara.getInt("upakaraId")
                val title = upakara.getString("title")
                val imgPath = upakara.getString("imgPath")
                val desc = upakara.getString("desc")

                val upakaraResponse = UpakaraResponse(upakaraId, title, imgPath, desc)
                list.add(upakaraResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

    fun loadShop(): List<ShopResponse> {
        val list = ArrayList<ShopResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("ShopResponses.json").toString())
            val listArray = responseObject.getJSONArray("shop")
            for (i in 0 until listArray.length()) {
                val shop = listArray.getJSONObject(i)

                val shopId = shop.getInt("shopId")
                val name = shop.getString("name")
                val imgPath = shop.getString("imgPath")
                val location = shop.getString("location")
                val tlp = shop.getString("tlp")
                val product = shop.getString("product")
                val desc = shop.getString("desc")

                val shopResponse = ShopResponse(shopId, name, imgPath, location, tlp, product, desc)
                list.add(shopResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }


    fun loadPhilosophy(): List<PhilosophyResponse> {
        val list = ArrayList<PhilosophyResponse>()
        try {
            val responseObject =
                JSONObject(parsingFileToString("PhilosophyResponses.json").toString())
            val listArray = responseObject.getJSONArray("philosophy")
            for (i in 0 until listArray.length()) {
                val philosophy = listArray.getJSONObject(i)

                val philosophyId = philosophy.getInt("philosophyId")
                val title = philosophy.getString("title")
                val imgPath = philosophy.getString("imgPath")
                val desc = philosophy.getString("desc")

                val philosophyResponse = PhilosophyResponse(philosophyId, title, imgPath, desc)
                list.add(philosophyResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }


}