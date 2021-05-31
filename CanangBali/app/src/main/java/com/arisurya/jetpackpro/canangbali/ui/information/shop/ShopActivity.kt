package com.arisurya.jetpackpro.canangbali.ui.information.shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.arisurya.jetpackpro.canangbali.R
import com.arisurya.jetpackpro.canangbali.databinding.ActivityShopBinding
import com.arisurya.jetpackpro.canangbali.ui.information.shop.detail.DetailShopViewModel

class ShopActivity : AppCompatActivity() {
    private lateinit var shopBinding : ActivityShopBinding
    private lateinit var viewModel: ShopViewModel
    private lateinit var adapter: ShopAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shopBinding = ActivityShopBinding.inflate(layoutInflater)
        setContentView(shopBinding.root)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[ShopViewModel::class.java]
        val shop = viewModel.getShop()
        adapter = ShopAdapter()
        adapter.setShop(shop)

        shopBinding.apply {
            rvShop.layoutManager = LinearLayoutManager(this@ShopActivity)
            rvShop.setHasFixedSize(true)
            rvShop.adapter = adapter
        }

    }
}