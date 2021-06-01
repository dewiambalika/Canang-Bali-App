package com.arisurya.jetpackpro.canangbali.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.CanangEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.PhilosophyEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.ShopEntity
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.UpakaraEntity

@Dao
interface CanangDao {
    @Query("SELECT * FROM canangentities")
    fun getCanang(): LiveData<List<CanangEntity>>

    @Query("SELECT * FROM upakaraentities")
    fun getUpakara(): LiveData<List<UpakaraEntity>>

    @Query("SELECT * FROM shopentities")
    fun getShop(): LiveData<List<ShopEntity>>

    @Query("SELECT * FROM philosophyentities")
    fun getPhilosophy(): LiveData<List<PhilosophyEntity>>

    @Query("SELECT * FROM canangentities WHERE canangId =:canangId")
    fun getDetailCanang(canangId: String): LiveData<CanangEntity>

    @Query("SELECT * FROM upakaraentities WHERE upakaraId =:upakaraId")
    fun getDetailUpakara(upakaraId: String): LiveData<UpakaraEntity>

    @Query("SELECT * FROM shopentities WHERE shopId =:shopId")
    fun getDetailShop(shopId: String): LiveData<ShopEntity>

    @Query("SELECT * FROM canangentities WHERE bookmarked=1")
    fun getBookmarkedCanang(): LiveData<List<CanangEntity>>

    @Query("SELECT * FROM upakaraentities WHERE bookmarked=1")
    fun getBookmarkedUpakara(): LiveData<List<UpakaraEntity>>

    @Query("SELECT * FROM shopentities WHERE bookmarked=1")
    fun getBookmarkedShop(): LiveData<List<ShopEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCanang(courses: List<CanangEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpakara(courses: List<UpakaraEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShop(courses: List<ShopEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhilosophy(courses: List<PhilosophyEntity>)

    @Update
    fun updateCanang(canang: CanangEntity)

    @Update
    fun updateUpakara(upakara: UpakaraEntity)

    @Update
    fun updateShope(shop: ShopEntity)


}