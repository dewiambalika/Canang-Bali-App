package com.arisurya.jetpackpro.canangbali.ui.dashboard.bookmark

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.arisurya.jetpackpro.canangbali.databinding.ActivityBookmarkCanangBinding
import com.arisurya.jetpackpro.canangbali.ui.information.canang.CanangAdapter
import com.arisurya.jetpackpro.canangbali.viewmodel.ViewModelFactory


class BookmarkCanangActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookmarkCanangBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookmarkCanangBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Bookmark Canang"

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[BookmarkCanangViewModel::class.java]

        val adapter = CanangAdapter()

        showProgressBar(true)
        viewModel.getBookmarkCanang().observe(this, { canang ->
            if (canang != null) {
                showProgressBar(false)
                adapter.setCanang(canang)
                adapter.notifyDataSetChanged()
            }
        })
        binding.apply {
            rvBookmarkCanang.layoutManager = LinearLayoutManager(this@BookmarkCanangActivity)
            rvBookmarkCanang.setHasFixedSize(true)
            rvBookmarkCanang.adapter = adapter
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