package com.example.gt_6m_4_youtube.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Localized(
    val description: String,
    val title: String
): Parcelable