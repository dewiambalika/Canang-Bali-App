package com.arisurya.jetpackpro.canangbali.ui.information.canang.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import com.arisurya.jetpackpro.canangbali.R
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.CanangEntity
import com.arisurya.jetpackpro.canangbali.databinding.ActivityDetailCanangBinding
import com.arisurya.jetpackpro.canangbali.databinding.ActivityDetailUpakaraBinding
import com.arisurya.jetpackpro.canangbali.ui.information.upakara.detail.DetailUpakaraActivity
import com.arisurya.jetpackpro.canangbali.ui.information.upakara.detail.DetailUpakaraViewModel
import com.arisurya.jetpackpro.canangbali.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide

class DetailCanangActivity : AppCompatActivity(), View.OnClickListener {
    companion object{
        const val EXTRA_CANANG = "extra_canang"
    }

    private lateinit var detailCanangBinding: ActivityDetailCanangBinding
    private lateinit var viewModel: DetailCanangViewModel
    private lateinit var shareMessage : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailCanangBinding = ActivityDetailCanangBinding.inflate(layoutInflater)
        setContentView(detailCanangBinding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailCanangViewModel::class.java]

        val extras = intent.extras
        showProgressBar(true)
        if(extras!=null){
            val canangId = extras.getString(EXTRA_CANANG)
            if(canangId!=null){
                viewModel.setSelectedCanang(canangId)
                viewModel.getDetailCanang().observe(this, {canang->
                    if(canang!=null){
                        showProgressBar(false)
                        populateCanang(canang)
                        shareMessage ="""
                        [INFO CANANG]
                        Judul            : ${canang.title}
                        Fungsi           : 
                        ${canang.function}            
                        Cara pembuatan   : 
                        ${canang.make}
                        
                        Created by Canang Bali Team            
                    """.trimIndent()
                    }

                })

            }
        }


    }

    private fun populateCanang(data: CanangEntity) {

        detailCanangBinding.apply {
            tvTitleDetailCanang.text = data.title
            descFunction.text = data.function
            descStep.text = data.make
            Glide.with(this@DetailCanangActivity)
                .load(data.imgPath)
                .into(imgCanang)
            btnShare.setOnClickListener(this@DetailCanangActivity)
        }

    }

    private fun shareDetailCanang() {
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder
            .from(this)
            .setType(mimeType)
            .setChooserTitle("Share via")
            .setText(shareMessage)
            .startChooser()
    }

    override fun onClick(v: View?) {
       when(v){
           detailCanangBinding.btnShare ->{
               shareDetailCanang()
           }
       }

    }
    private fun showProgressBar(state : Boolean){
        if(state){
            detailCanangBinding.progressBar.visibility = View.VISIBLE
        }else{
            detailCanangBinding.progressBar.visibility = View.GONE
        }
    }
}