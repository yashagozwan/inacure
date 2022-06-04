package com.yashagozwan.inacure.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.File

@Parcelize
data class MyImage(val image: File, val from: String): Parcelable