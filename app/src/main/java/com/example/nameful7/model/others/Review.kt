package com.example.nameful7.model.others

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
class Review (
    @field:SerializedName("author")
    val author: String?,
    @field:SerializedName("author_details")
    val authorDetails: AuthorDetails,
    @field:SerializedName("content")
    val content: String?,
    @field:SerializedName("created_at")
    val createdAt: Date?,
    @field:SerializedName("id")
    val id: String?,
    @field:SerializedName("updated_at")
    val updatedAt: Date?,
    @field:SerializedName("url")
    val url: String?
): Parcelable