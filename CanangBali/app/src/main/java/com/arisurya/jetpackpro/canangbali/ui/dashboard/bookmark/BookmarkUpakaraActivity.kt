package com.arisurya.jetpackpro.canangbali.ui.dashboard.bookmark

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.arisurya.jetpackpro.canangbali.databinding.ActivityBookmarkUpakaraBinding
import com.arisurya.jetpackpro.canangbali.ui.information.upakara.UpakaraAdapter
import com.arisurya.jetpackpro.canangbali.viewmodel.ViewModelFactory

class BookmarkUpakaraActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookmarkUpakaraBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookmarkUpakaraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Bookmark Upacara"

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[BookmarkUpakaraViewModel::class.java]

        val adapter = UpakaraAdapter()

        showProgressBar(true)
        viewModel.getBookmarkUpakara().observe(this, { upakara ->
            if (upakara != null) {
                showProgressBar(false)
                adapter.setUpakara(upakara)
                adapter.notifyDataSetChanged()
            }
        })
        binding.apply {
            rvBookmarkUpakara.layoutManager = LinearLayoutManager(this@BookmarkUpakaraActivity)
            rvBookmarkUpakara.setHasFixedSize(true)
            rvBookmarkUpakara.adapter = adapter
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