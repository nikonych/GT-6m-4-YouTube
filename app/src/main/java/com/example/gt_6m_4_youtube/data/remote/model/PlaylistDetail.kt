package com.example.gt_6m_4_youtube.data.remote.model




import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class PlaylistDetail(
    val etag: String,
    val items: List<ItemDetail>,
    val kind: String,
    val pageInfo: PageInfoDetail,
    val nextPageToken: String
) : Parcelable

@Parcelize
data class ItemDetail(
    val etag: String,
    val id: String,
    val kind: String,
    val snippet: SnippetDetail
) : Parcelable

@Parcelize
data class PageInfoDetail(
    val resultsPerPage: Int,
    val totalResults: Int
): Parcelable

@Parcelize
data class SnippetDetail(
    val channelId: String,
    val channelTitle: String,
    val description: String,
    val playlistId: String,
    val position: Int,
    val publishedAt: String,
    val resourceId: ResourceId,
    val thumbnails: ThumbnailsDetail,
    val title: String,
    val videoOwnerChannelId: String,
    val videoOwnerChannelTitle: String
) : Parcelable

@Parcelize
data class ResourceId(
    val kind: String,
    val videoId: String
) : Parcelable

@Parcelize
data class ThumbnailsDetail(
    val default: DefaultDetail,
    val high: HighDetail,
    val maxres: MaxresDetail,
    val medium: MediumDetail,
    val standard: StandardDetail
) : Parcelable

@Parcelize
data class DefaultDetail(
    val height: Int,
    val url: String,
    val width: Int
): Parcelable

@Parcelize
data class HighDetail(
    val height: Int,
    val url: String,
    val width: Int
): Parcelable

@Parcelize
data class MaxresDetail(
    val height: Int,
    val url: String,
    val width: Int
): Parcelable

@Parcelize
data class MediumDetail(
    val height: Int,
    val url: String,
    val width: Int
): Parcelable

@Parcelize
data class StandardDetail(
    val height: Int,
    val url: String,
    val width: Int
) : Parcelable