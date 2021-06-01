package com.arisurya.jetpackpro.canangbali.ui.information.shop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.arisurya.jetpackpro.canangbali.R
import com.arisurya.jetpackpro.canangbali.databinding.ActivityShopBinding
import com.arisurya.jetpackpro.canangbali.ui.information.shop.detail.DetailShopViewModel
import com.arisurya.jetpackpro.canangbali.viewmodel.ViewModelFactory
import com.arisurya.jetpackpro.canangbali.vo.Status

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
                when(shop.status){
                    Status.LOADING -> showProgressBar(true)
                    Status.SUCCESS->{
                        showProgressBar(false)
                        adapter.setShop(shop.data)
                        adapter.notifyDataSetChanged()
                    }
                    Status.ERROR ->{
                        showProgressBar(false)
                        Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }

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