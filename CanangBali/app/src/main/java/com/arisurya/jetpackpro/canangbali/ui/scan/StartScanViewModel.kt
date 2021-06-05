package com.arisurya.jetpackpro.canangbali.ui.scan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arisurya.jetpackpro.canangbali.data.source.CanangRepository
import java.io.File

class StartScanViewModel (private val mCanangRepository: CanangRepository) : ViewModel() {

    fun uploadPhotoPredict(body : UploadRequestBody, file : File, contentType : String) : LiveData<String> =
        mCanangRepository.getResultDetection(body,file,contentType)
}