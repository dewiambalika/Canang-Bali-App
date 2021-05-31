package com.arisurya.jetpackpro.canangbali.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UpakaraResponse(
    var upakaraId: Int,
    var title: String,
    var imgPath: String,
    var desc: String
) : Parcelable