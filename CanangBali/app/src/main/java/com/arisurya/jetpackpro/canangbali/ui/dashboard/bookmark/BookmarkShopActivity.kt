package com.arisurya.jetpackpro.canangbali.ui.dashboard.bookmark

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.arisurya.jetpackpro.canangbali.databinding.ActivityBookmarkShopBinding
import com.arisurya.jetpackpro.canangbali.ui.information.shop.ShopAdapter
import com.arisurya.jetpackpro.canangbali.viewmodel.ViewModelFactory

class BookmarkShopActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookmarkShopBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookmarkShopBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Bookmark Toko"

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[BookmarkShopViewModel::class.java]

        val adapter = ShopAdapter()

        showProgressBar(true)
        viewModel.getBookmarkShop().observe(this, { shop ->
            if (shop != null) {
                showProgressBar(false)
                adapter.setShop(shop)
                adapter.notifyDataSetChanged()
            }
        })
        binding.apply {
            rvBookmarkShop.layoutManager = LinearLayoutManager(this@BookmarkShopActivity)
            rvBookmarkShop.setHasFixedSize(true)
            rvBookmarkShop.adapter = adapter
        }
    }

    private fun showProgressBar(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}