package com.arisurya.jetpackpro.canangbali.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CanangResponse(
    var canangId : Int,
    var title : String,
    var imgPath : String,
    var function : String,
    var make : String
) : Parcelable
