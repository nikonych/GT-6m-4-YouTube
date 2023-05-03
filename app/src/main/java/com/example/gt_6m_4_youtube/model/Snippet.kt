package com.example.gt_6m_4_youtube.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Snippet(
    val channelId: String,
    val channelTitle: String,
    val description: String,
    val localized: Localized,
    val publishedAt: String,
    val thumbnails: Thumbnails,
    val title: String
): Parcelable