package com.example.gt_6m_4_youtube.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    val contentDetails: ContentDetails,
    val etag: String,
    val id: String,
    val kind: String,
    val snippet: Snippet
): Parcelable