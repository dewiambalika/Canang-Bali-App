package com.arisurya.jetpackpro.canangbali.ui.information.upakara

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.arisurya.jetpackpro.canangbali.R
import com.arisurya.jetpackpro.canangbali.databinding.ActivityDetailUpakaraBinding
import com.arisurya.jetpackpro.canangbali.databinding.ActivityUpakaraBinding
import com.arisurya.jetpackpro.canangbali.ui.information.upakara.detail.DetailUpakaraViewModel

class UpakaraActivity : AppCompatActivity() {

    private lateinit var upakaraBinding: ActivityUpakaraBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        upakaraBinding = ActivityUpakaraBinding.inflate(layoutInflater)
        setContentView(upakaraBinding.root)

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[UpakaraViewModel::class.java]

        val adapter = UpakaraAdapter()
        val upakara = viewModel.getUpakara()
        adapter.setUpakara(upakara)

        upakaraBinding.apply {
            rvUpakara.layoutManager = LinearLayoutManager(this@UpakaraActivity)
            rvUpakara.setHasFixedSize(true)
            rvUpakara.adapter = adapter
        }
    }
}