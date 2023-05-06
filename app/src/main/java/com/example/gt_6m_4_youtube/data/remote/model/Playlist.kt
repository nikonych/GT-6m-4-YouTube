package com.example.gt_6m_4_youtube.data.remote.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Playlist(
    val etag: String,
    val items: List<Item>,
    val kind: String,
    val nextPageToken: String,
    val pageInfo: PageInfo
): Parcelable

@Parcelize
data class Item(
    val contentDetails: ContentDetails,
    val etag: String,
    val id: String,
    val kind: String,
    val snippet: Snippet
): Parcelable

@Parcelize
data class PageInfo(
    val resultsPerPage: Int,
    val totalResults: Int
): Parcelable

@Parcelize
data class ContentDetails(
    val itemCount: Int
): Parcelable

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

@Parcelize
data class Localized(
    val description: String,
    val title: String
): Parcelable

@Parcelize
data class Thumbnails(
    val default: Default,
    val high: High,
    val maxres: Maxres,
    val medium: Medium,
    val standard: Standard
): Parcelable

@Parcelize
data class Default(
    val height: Int,
    val url: String,
    val width: Int
): Parcelable

@Parcelize
data class High(
    val height: Int,
    val url: String,
    val width: Int
): Parcelable

@Parcelize
data class Maxres(
    val height: Int,
    val url: String,
    val width: Int
): Parcelable

@Parcelize
data class Medium(
    val height: Int,
    val url: String,
    val width: Int
): Parcelable

@Parcelize
data class Standard(
    val height: Int,
    val url: String,
    val width: Int
) : Parcelable