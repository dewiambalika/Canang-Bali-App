package com.arisurya.jetpackpro.canangbali.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShopResponse(
    var shopId : Int,
    var name : String,
    var imgPath : String,
    var location : String,
    var tlp : String,
    var product : String,
    var desc : String
) : Parcelable