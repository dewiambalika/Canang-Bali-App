package com.arisurya.jetpackpro.canangbali.ui.information.philosophy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import com.arisurya.jetpackpro.canangbali.R
import com.arisurya.jetpackpro.canangbali.databinding.ActivityPhilosophyBinding
import com.bumptech.glide.Glide

class PhilosophyActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var philosophyBinding: ActivityPhilosophyBinding
    private lateinit var viewModel: PhilosophyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        philosophyBinding = ActivityPhilosophyBinding.inflate(layoutInflater)
        setContentView(philosophyBinding.root)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[PhilosophyViewModel::class.java]
        val philosophy = viewModel.getPhilosophy()
        philosophyBinding.apply {
            tvTitleDetailPhi.text = philosophy.title
            tvDescPhi.text = philosophy.desc
            btnShare.setOnClickListener(this@PhilosophyActivity)
            Glide.with(this@PhilosophyActivity)
                .load(philosophy.imgPath)
                .into(imgPhi)

        }
    }

    override fun onClick(v: View?) {
        when (v) {
            philosophyBinding.btnShare -> {
                sharePhilosophy()
            }
        }

    }

    private fun sharePhilosophy() {
        val data = viewModel.getPhilosophy()
        val shareMessage = """
            [INFO FILOSOFI CANANG]
            Judul      : ${data.title}
            Penjelasan : 
            ${data.desc}
            
            Created by Canang Bali Team  
        """.trimIndent()
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder
            .from(this)
            .setType(mimeType)
            .setChooserTitle("Share via")
            .setText(shareMessage)
            .startChooser()
    }
}