package com.arisurya.jetpackpro.canangbali.ui.scan

import android.app.Dialog
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
import com.arisurya.jetpackpro.canangbali.R
import com.arisurya.jetpackpro.canangbali.databinding.ActivityStartScanBinding
import com.arisurya.jetpackpro.canangbali.ml.Model
import com.arisurya.jetpackpro.canangbali.ui.information.canang.detail.DetailCanangActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import java.nio.ByteOrder

@Suppress("DEPRECATION")
class StartScanActivity : AppCompatActivity() {
    private lateinit var bitmap: Bitmap
    private lateinit var binding : ActivityStartScanBinding
    private lateinit var canangId : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fileName  = "labels.txt"
        val inputString = application.assets.open(fileName).bufferedReader().use { it.readText() }
        var townList = inputString.split("\n")

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
            var resized : Bitmap = Bitmap.createScaledBitmap(bitmap, 300,300, true)

            val model = Model.newInstance(this)

            // Creates inputs for reference.
            val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 300, 300, 3), DataType.FLOAT32)

            var byteBuffer = convertBitmapToByteBuffer(resized)

            inputFeature0.loadBuffer(byteBuffer)

            // Runs model inference and gets result.
            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer

            var max = getMax(outputFeature0.floatArray)
//            var value = getValueArrayFloat(outputFeature0.floatArray)
            binding.textView.setText(townList[max])

            // Releases model resources if no longer used.
            model.close()
            showDialog(max.toString())

        })


    }

    fun getMax (arr : FloatArray):Int{
        var ind = 0
        var min = 0.0f
        for (i in arr.indices){
            if(arr[i]>min){
                ind = i
                min = arr[i]
            }
        }

        return ind
    }

    fun getValueArrayFloat (arr : FloatArray):String{
        var ind = 0
        var min = 0.0f
        var str = ""
        for (i in arr.indices){
            str += ",${arr[i]}"
        }

        return str
    }

    private fun convertBitmapToByteBuffer(bitmap: Bitmap): ByteBuffer {
        val byteBuffer = ByteBuffer.allocateDirect(4 * 300 * 300 * 3)
        byteBuffer.order(ByteOrder.nativeOrder())
        val intValues = IntArray(300 * 300)

        bitmap.getPixels(intValues, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)
        var pixel = 0
        for (i in 0 until 300) {
            for (j in 0 until 300) {
                val input = intValues[pixel++]

                byteBuffer.putFloat((((input.shr(16)  and 0xFF) - 0) / 255.0f))
                byteBuffer.putFloat((((input.shr(8) and 0xFF) - 0) / 255.0f))
                byteBuffer.putFloat((((input and 0xFF) - 0) / 255.0f))
            }
        }
        return byteBuffer
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==101){
            var pic = data?.getParcelableExtra<Bitmap>("data")
            binding.imageView.setImageBitmap(pic)
            bitmap = pic!!
        }

        if(requestCode==100){
            binding.imageView.setImageURI(data?.data)

            var uri : Uri? = data?.data
            bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
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

    private fun showDialog(index : String) {
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

}