package com.arisurya.jetpackpro.canangbali.ui.information.philosophy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import com.arisurya.jetpackpro.canangbali.R
import com.arisurya.jetpackpro.canangbali.data.source.local.entity.PhilosophyEntity
import com.arisurya.jetpackpro.canangbali.databinding.ActivityPhilosophyBinding
import com.arisurya.jetpackpro.canangbali.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide

class PhilosophyActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var philosophyBinding: ActivityPhilosophyBinding
    private lateinit var viewModel: PhilosophyViewModel

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
        viewModel.getPhilosophy().observe(this@PhilosophyActivity, {data->
            if(data!=null){
                showProgressBar(false)
                populatePhilosophy(data)
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

    private fun populatePhilosophy(philosophy : PhilosophyEntity){
        philosophyBinding.apply {
            tvTitleDetailPhi.text = philosophy.title
            tvDescPhi.text = philosophy.desc
            btnShare.setOnClickListener(this@PhilosophyActivity)
            Glide.with(this@PhilosophyActivity)
                .load(philosophy.imgPath)
                .into(imgPhi)

        }
    }

    private fun sharePhilosophy() {
        lateinit var shareMessage: String
       viewModel.getPhilosophy().observe(this@PhilosophyActivity, {data->
           shareMessage = """
            [INFO FILOSOFI CANANG]
            Judul      : ${data.title}
            Penjelasan : 
            ${data.desc}
            
            Created by Canang Bali Team  
        """.trimIndent()
           val mimeType = "text/plain"
           ShareCompat.IntentBuilder
               .from(this@PhilosophyActivity)
               .setType(mimeType)
               .setChooserTitle("Share via")
               .setText(shareMessage)
               .startChooser()
       })
    }

    private fun showProgressBar(state : Boolean){
        if(state){
            philosophyBinding.progressBar.visibility = View.VISIBLE
        }else{
            philosophyBinding.progressBar.visibility = View.GONE
        }
    }
}