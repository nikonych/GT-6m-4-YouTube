package com.example.gt_6m_4_youtube.data.remote.model




import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Video(
    val etag: String,
    val items: List<ItemVideo>,
    val kind: String,
    val pageInfo: PageInfoVideo
) : Parcelable

@Parcelize
data class ItemVideo(
    val contentDetails: ContentDetailsVideo,
    val etag: String,
    val id: String,
    val kind: String
) : Parcelable

@Parcelize
data class PageInfoVideo(
    val resultsPerPage: Int,
    val totalResults: Int
) : Parcelable

@Parcelize
data class ContentDetailsVideo(
    val caption: String,
    val contentRating: ContentRatingVideo,
    val definition: String,
    val dimension: String,
    val duration: String,
    val licensedContent: Boolean,
    val projection: String
) : Parcelable

@Parcelize
class ContentRatingVideo : Parcelable