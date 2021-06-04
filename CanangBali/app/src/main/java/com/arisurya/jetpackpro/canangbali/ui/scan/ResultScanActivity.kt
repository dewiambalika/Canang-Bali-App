package com.arisurya.jetpackpro.canangbali.ui.scan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ShareCompat
import com.arisurya.jetpackpro.canangbali.data.source.remote.response.ShopResponse
import com.arisurya.jetpackpro.canangbali.databinding.ActivityResultScanBinding
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ResultScanActivity : AppCompatActivity(), View.OnClickListener {
    companion object{
        const val EXTRA_CANANG = "extra_canang"
    }
    private lateinit var binding : ActivityResultScanBinding
    private lateinit var title : String
    private lateinit var function : String
    private lateinit var make : String
    private lateinit var imgPath : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        val canangId = extras?.getString(EXTRA_CANANG)

        var firebase = FirebaseDatabase.getInstance().getReference("canang")

        showProgressBar(true)
        var getData = object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var sb = StringBuilder()
                    for(data in snapshot.children){
                        var c = data.child("title").getValue()
                        sb.append(c)
                        if(data.key == canangId){
                                title = data.child("title").getValue().toString()
                                function = data.child("function").getValue().toString()
                                make = data.child("make").getValue().toString()
                                imgPath = data.child("imgPath").getValue().toString()
                                showProgressBar(false)

                        }
                    }
                binding.apply {
                    tvTitleDetailCanang.text = title
                    descFunction.text = function
                    descStep.text = make
                    Glide.with(this@ResultScanActivity)
                        .load(imgPath)
                        .into(imgCanang)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        }

        firebase.addValueEventListener(getData)
        firebase.addListenerForSingleValueEvent(getData)

        binding.btnShare.setOnClickListener(this)



    }

    private fun showProgressBar(state : Boolean){
        if(state){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onClick(v: View?) {
        when(v){
            binding.btnShare -> {
                shareDetailCanang()
            }
        }
    }

    private fun shareDetailCanang() {
        val shareMessage = """
            [Info Canang]
         Nama Canang     : ${title}
         Fungsi Canang   : 
            ${function}
         Cara Kerja      :
            ${make}
            
            Created by Canang Bali
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