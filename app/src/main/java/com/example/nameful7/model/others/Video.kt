package com.example.nameful7.model.others

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Video(
    @field:SerializedName("name")
    val name: String?,
    @field:SerializedName("key")
    val key: String?,
    @field:SerializedName("official")
    val official: Boolean?,
    @field:SerializedName("id")
    val id: String?
): Parcelable