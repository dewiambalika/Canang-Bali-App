package com.arisurya.jetpackpro.canangbali.ui.information.upakara.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import com.arisurya.jetpackpro.canangbali.R
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.UpakaraEntity
import com.arisurya.jetpackpro.canangbali.databinding.ActivityDetailUpakaraBinding
import com.arisurya.jetpackpro.canangbali.viewmodel.ViewModelFactory
import com.arisurya.jetpackpro.canangbali.vo.Status
import com.bumptech.glide.Glide
import kotlin.concurrent.fixedRateTimer

class DetailUpakaraActivity : AppCompatActivity(), View.OnClickListener {

    companion object{
        const val EXTRA_UPAKARA ="extra_upkara"
    }
    private lateinit var detailUpakaraBinding: ActivityDetailUpakaraBinding
    private lateinit var viewModel: DetailUpakaraViewModel
    private lateinit var shareMessage : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailUpakaraBinding = ActivityDetailUpakaraBinding.inflate(layoutInflater)
        setContentView(detailUpakaraBinding.root)
        supportActionBar?.title = "Detail Upakara"

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailUpakaraViewModel::class.java]

        val extras = intent.extras
        if(extras!=null){
            val upakaraId = extras.getString(EXTRA_UPAKARA)
            showProgressBar(true)
            if(upakaraId!=null){
                viewModel.setSelectedUpakara(upakaraId)
                viewModel.detailUpakara.observe(this, {upakara->
                    if (upakara!=null){
                        when(upakara.status){
                            Status.LOADING -> showProgressBar(true)
                            Status.SUCCESS ->{
                                showProgressBar(false)
                                populateUpakara(upakara.data)
                                shareMessage = """
                                    [INFO UPAKARA]
                                    Judul      : ${upakara.data?.title}
                                    Penjelasan : 
                                    ${upakara.data?.desc}
                                    
                                    Created by Canang Bali Team            
                                """.trimIndent()

                                val state = upakara.data?.bookmarked
                                setBookmarkState(state)
                            }
                            Status.ERROR -> {
                                showProgressBar(false)
                                Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }

                    }
                })

            }
        }

    }

    private fun populateUpakara(upakara: UpakaraEntity?) {
        detailUpakaraBinding.apply {
            tvTitleDetailUpakara.text = upakara?.title
            tvDesc.text = upakara?.desc
            Glide.with(this@DetailUpakaraActivity)
                .load(upakara?.imgPath)
                .into(imgUpakara)
            btnShare.setOnClickListener(this@DetailUpakaraActivity)
            btnBookmark.setOnClickListener(this@DetailUpakaraActivity)
        }
    }

    override fun onClick(v: View?) {
        when(v){
            detailUpakaraBinding.btnShare ->{
                shareDetailUpakara()
            }
            detailUpakaraBinding.btnBookmark -> {
                setBookmarkUpakara()
            }
        }

    }

    private fun shareDetailUpakara() {

        val mimeType = "text/plain"
        ShareCompat.IntentBuilder
            .from(this)
            .setType(mimeType)
            .setChooserTitle("Share via")
            .setText(shareMessage)
            .startChooser()
    }

    private fun showProgressBar(state : Boolean){
        if(state){
            detailUpakaraBinding.progressBar.visibility = View.VISIBLE
        }else{
            detailUpakaraBinding.progressBar.visibility = View.GONE
        }
    }

    private fun showToastAddBookmark(){
        val toastView = layoutInflater.inflate(
            R.layout.toast_success, findViewById(R.id.toast_add)
        )
        with(Toast(applicationContext)){
            duration = Toast.LENGTH_SHORT
            view = toastView
            show()
        }
    }

    private fun showToastRemoveBookmark(){
        val toastView = layoutInflater.inflate(
            R.layout.toast_remove, findViewById(R.id.toast_remove)
        )
        with(Toast(applicationContext)){
            duration = Toast.LENGTH_SHORT
            view = toastView
            show()
        }
    }

    private fun setBookmarkState(state : Boolean?){
        if(state ==true) detailUpakaraBinding.btnBookmark.setImageResource(R.drawable.ic_bookmark)
        else detailUpakaraBinding.btnBookmark.setImageResource(R.drawable.ic_bookmark_outline)
    }

    private fun setBookmarkUpakara(){
        if(viewModel.detailUpakara.value?.data?.bookmarked == true) showToastRemoveBookmark()
        else showToastAddBookmark()
        viewModel.setBookmarkUpakara()
    }
}