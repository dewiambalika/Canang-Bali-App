package com.arisurya.jetpackpro.canangbali.ui.scan

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.arisurya.jetpackpro.canangbali.R
import com.arisurya.jetpackpro.canangbali.databinding.ActivityStartScanBinding
import com.arisurya.jetpackpro.canangbali.utils.getFileName
import com.arisurya.jetpackpro.canangbali.viewmodel.ViewModelFactory
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


@Suppress("DEPRECATION")
class StartScanActivity : AppCompatActivity(), UploadRequestBody.UploadCallback {
    private lateinit var bitmap: Bitmap
    private lateinit var binding : ActivityStartScanBinding
    private lateinit var canangId : String
    private lateinit var resultPredict : String

    private var selectedImageUri: Uri? = null
    private lateinit var viewModel: StartScanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel  = ViewModelProvider(this, factory)[StartScanViewModel::class.java]

        binding.btnOpenCam.isEnabled = true
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA),111)
        }else{
            binding.btnOpenCam.isEnabled = true
        }

        binding.btnOpenCam.setOnClickListener {
            val i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(i, 101)
        }

        binding.btnSelect.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, 100)
        })


        binding.btnPredict.setOnClickListener(View.OnClickListener {
            showDialog()
        })


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==101){
            var pic = data?.getParcelableExtra<Bitmap>("data")
            binding.imageView.setImageBitmap(pic)
            bitmap = pic!!
            selectedImageUri = getImageUri(this, bitmap)
        }

        if(requestCode==100){
            binding.imageView.setImageURI(data?.data)

            var uri : Uri? = data?.data
            bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
            selectedImageUri = data?.data
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            binding.btnOpenCam.isEnabled = true
        }

    }

    private fun showDialog() {
        val dialog = Dialog(this@StartScanActivity)
        dialog.window?.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.alert_dialog)
        dialog.setCancelable(true)

        @Suppress("LocalVariableName")
        val btn_detail = dialog.findViewById<Button>(R.id.btn_go_detail)
        val btn_cancel = dialog.findViewById<Button>(R.id.btn_cancel)
        val tv_result = dialog.findViewById<TextView>(R.id.tv_result)
        val progressBar = dialog.findViewById<ProgressBar>(R.id.progress_bar)


        if(selectedImageUri!=null){
            val parcelFileDescriptor = contentResolver.openFileDescriptor(selectedImageUri!!, "r", null)

            val inputStream = FileInputStream(parcelFileDescriptor?.fileDescriptor)
            val file = File(cacheDir, contentResolver.getFileName(selectedImageUri!!))
            val outputStream = FileOutputStream(file)
            inputStream.copyTo(outputStream)
            val body = UploadRequestBody(file, "image", this)

            viewModel.uploadPhotoPredict(body, file,"image").observe(this, {data->
                binding.textView.text = data
                resultPredict = data

                var index = getIdCanang(data)

                var database = FirebaseDatabase.getInstance().getReference("canang")
                progressBar.visibility = View.VISIBLE
                var getData = object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        var sb = StringBuilder()
                        var id = StringBuilder()
                        for(i in snapshot.children){
                            if(i.key == index){
                                var name = i.child("title").getValue()
                                var idCanang = i.child("canangId").getValue()
                                sb.append("$name")
                                id.append(idCanang)
                                canangId = id.toString()
                            }
                        }
                        tv_result.setText(sb)
                        progressBar.visibility = View.GONE
                    }

                    override fun onCancelled(error: DatabaseError) {

                    }

                }
                database.addValueEventListener(getData)
                database.addListenerForSingleValueEvent(getData)
            })

        }else{
            Toast.makeText(this, "Maaf, foto masih kosong", Toast.LENGTH_SHORT).show()
        }




        btn_detail.setOnClickListener {
            val intent = Intent(this, ResultScanActivity::class.java)
            intent.putExtra(ResultScanActivity.EXTRA_CANANG, canangId)
            startActivity(intent)
        }
        btn_cancel.setOnClickListener{
            dialog.cancel()
        }

        dialog.show()
    }

    override fun onProgressUpdate(percentage: Int) {

    }

    @Suppress("DEPRECATION")
    private fun getImageUri(context: Context, image: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(context.contentResolver, bitmap, "Title", null)
        return Uri.parse(path.toString())
    }

    fun getIdCanang(title : String) : String {
        return when(title){
            "Canang Goak"->"0"
            "Canang Ituk-Ituk"->"1"
            "Canang Sari" ->"2"
            "Canang Genten"->"3"
            else -> ""
        }
    }

}