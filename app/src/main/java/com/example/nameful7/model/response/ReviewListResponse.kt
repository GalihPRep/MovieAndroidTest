package com.example.nameful7.model.response

import android.os.Parcelable
import com.example.nameful7.model.others.Review
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReviewListResponse (
    @field:SerializedName("id")
    val id: Int?,

    @field:SerializedName("page")
    val page: Int?,

    @field:SerializedName("results")
    val results: MutableList<Review>,

    @field:SerializedName("total_pages")
    val totalPages: Int?,

    @field:SerializedName("total_results")
    val totalResults: Int?,
): Parcelable