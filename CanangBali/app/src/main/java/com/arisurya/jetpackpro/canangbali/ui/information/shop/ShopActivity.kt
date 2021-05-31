package com.arisurya.jetpackpro.canangbali.ui.information.shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.arisurya.jetpackpro.canangbali.R
import com.arisurya.jetpackpro.canangbali.databinding.ActivityShopBinding
import com.arisurya.jetpackpro.canangbali.ui.information.shop.detail.DetailShopViewModel
import com.arisurya.jetpackpro.canangbali.viewmodel.ViewModelFactory

class ShopActivity : AppCompatActivity() {
    private lateinit var shopBinding : ActivityShopBinding
    private lateinit var viewModel: ShopViewModel
    private lateinit var adapter: ShopAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shopBinding = ActivityShopBinding.inflate(layoutInflater)
        setContentView(shopBinding.root)

        val factory = ViewModelFactory.getInstance(this)

        viewModel = ViewModelProvider(this, factory)[ShopViewModel::class.java]

        adapter = ShopAdapter()
        showProgressBar(true)
        viewModel.getShop().observe(this, {shop->
            if (shop!=null){
                showProgressBar(false)
                adapter.setShop(shop)
                adapter.notifyDataSetChanged()
            }

        })

        shopBinding.apply {
            rvShop.layoutManager = LinearLayoutManager(this@ShopActivity)
            rvShop.setHasFixedSize(true)
            rvShop.adapter = adapter
        }

    }

    private fun showProgressBar(state : Boolean){
        if(state){
            shopBinding.progressBar.visibility = View.VISIBLE
        }else{
            shopBinding.progressBar.visibility = View.GONE
        }
    }
}