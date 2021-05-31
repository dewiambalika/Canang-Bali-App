package com.arisurya.jetpackpro.canangbali.ui.information.upakara.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.arisurya.jetpackpro.canangbali.R
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.UpakaraEntity
import com.arisurya.jetpackpro.canangbali.databinding.ActivityDetailUpakaraBinding
import com.bumptech.glide.Glide

class DetailUpakaraActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_UPAKARA ="extra_upkara"
    }
    private lateinit var detailUpakaraBinding: ActivityDetailUpakaraBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailUpakaraBinding = ActivityDetailUpakaraBinding.inflate(layoutInflater)
        setContentView(detailUpakaraBinding.root)

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailUpakaraViewModel::class.java]

        val extras = intent.extras
        if(extras!=null){
            val upakaraId = extras.getString(EXTRA_UPAKARA)
            if(upakaraId!=null){
                viewModel.setSelectedUpakara(upakaraId)
                populateUpakara(viewModel.getDetailUpakara())
            }
        }

    }

    private fun populateUpakara(upakara: UpakaraEntity) {
        detailUpakaraBinding.apply {
            tvTitleDetailUpakara.text = upakara.title
            tvDesc.text = upakara.desc
            Glide.with(this@DetailUpakaraActivity)
                .load(upakara.imgPath)
                .into(imgUpakara)
        }
    }
}