package com.betylf.b_corner.ui.test

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Question(
    val text: String,
    var isChecked: Boolean = false
) : Parcelable