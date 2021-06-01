package com.arisurya.jetpackpro.canangbali.ui.information.philosophy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import com.arisurya.jetpackpro.canangbali.R
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.PhilosophyEntity
import com.arisurya.jetpackpro.canangbali.databinding.ActivityPhilosophyBinding
import com.arisurya.jetpackpro.canangbali.viewmodel.ViewModelFactory
import com.arisurya.jetpackpro.canangbali.vo.Status
import com.bumptech.glide.Glide

class PhilosophyActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var philosophyBinding: ActivityPhilosophyBinding
    private lateinit var viewModel: PhilosophyViewModel
    private lateinit var shareMessage : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        philosophyBinding = ActivityPhilosophyBinding.inflate(layoutInflater)
        setContentView(philosophyBinding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(
            this,
            factory
        )[PhilosophyViewModel::class.java]
        showProgressBar(true)
        viewModel.getPhilosophy().observe(this@PhilosophyActivity, {phi->
            if(phi!=null){
                when(phi.status){
                    Status.LOADING -> showProgressBar(true)
                    Status.SUCCESS ->{
                        showProgressBar(false)
                        populatePhilosophy(phi.data?.get(0))
                        shareMessage = """
                            [INFO FILOSOFI CANANG]
                            Judul      : ${phi.data?.get(0)?.title}
                            Penjelasan : 
                            ${phi.data?.get(0)?.desc}
                            
                            Created by Canang Bali Team  
                        """.trimIndent()
                    }
                    Status.ERROR ->{
                        Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        })

    }

    override fun onClick(v: View?) {
        when (v) {
            philosophyBinding.btnShare -> {
                sharePhilosophy()
            }
        }

    }

    private fun populatePhilosophy(philosophy : PhilosophyEntity?){
        philosophyBinding.apply {
            tvTitleDetailPhi.text = philosophy?.title
            tvDescPhi.text = philosophy?.desc
            btnShare.setOnClickListener(this@PhilosophyActivity)
            Glide.with(this@PhilosophyActivity)
                .load(philosophy?.imgPath)
                .into(imgPhi)

        }
    }

    private fun sharePhilosophy() {
           val mimeType = "text/plain"
           ShareCompat.IntentBuilder
               .from(this@PhilosophyActivity)
               .setType(mimeType)
               .setChooserTitle("Share via")
               .setText(shareMessage)
               .startChooser()
    }

    private fun showProgressBar(state : Boolean){
        if(state){
            philosophyBinding.progressBar.visibility = View.VISIBLE
        }else{
            philosophyBinding.progressBar.visibility = View.GONE
        }
    }
}