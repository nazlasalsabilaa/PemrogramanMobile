package com.nazlasalsabila.global_icons

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Icon(
    val name: String,
    val location: String,
    val photo: Int,
    val descriptionRes: Int,
    val url: String
) : Parcelable