package com.example.nameful7.model.response

import android.os.Parcelable
import com.example.nameful7.model.others.Video
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoListResponse (
    @field:SerializedName("id")
    val id: String?,

    @field:SerializedName("results")
    val results: List<Video>
): Parcelable