package com.example.gt_6m_4_youtube.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Thumbnails(
    val default: Default,
    val high: High,
    val maxres: Maxres,
    val medium: Medium,
    val standard: Standard
): Parcelable