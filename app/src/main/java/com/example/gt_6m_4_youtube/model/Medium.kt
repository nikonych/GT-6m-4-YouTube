package com.example.gt_6m_4_youtube.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Medium(
    val height: Int,
    val url: String,
    val width: Int
): Parcelable