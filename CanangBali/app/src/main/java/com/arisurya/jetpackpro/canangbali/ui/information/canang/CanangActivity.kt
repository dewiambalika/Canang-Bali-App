package com.arisurya.jetpackpro.canangbali.ui.information.canang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.arisurya.jetpackpro.canangbali.R
import com.arisurya.jetpackpro.canangbali.databinding.ActivityCanangBinding
import com.arisurya.jetpackpro.canangbali.ui.information.upakara.UpakaraAdapter
import com.arisurya.jetpackpro.canangbali.ui.information.upakara.UpakaraViewModel

class CanangActivity : AppCompatActivity() {
    private lateinit var canangBinding : ActivityCanangBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        canangBinding = ActivityCanangBinding.inflate(layoutInflater)
        setContentView(canangBinding.root)

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[CanangViewModel::class.java]

        val adapter = CanangAdapter()
        val canang = viewModel.getCanang()
        adapter.setCanang(canang)

        canangBinding.apply {
            rvCanang.layoutManager = LinearLayoutManager(this@CanangActivity)
            rvCanang.setHasFixedSize(true)
            rvCanang.adapter = adapter
        }
    }


}