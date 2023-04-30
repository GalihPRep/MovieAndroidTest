package com.example.nameful7.model.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.example.nameful7.model.others.Movie
import com.google.gson.annotations.SerializedName

@Parcelize
data class MovieListResponse(
    @field:SerializedName("page")
	val page: Int?,

    @field:SerializedName("results")
	val results: MutableList<Movie>,

    @field:SerializedName("total_pages")
    val totalPages: Int?,

    @field:SerializedName("total_results")
	val totalResults: Int?,
) : Parcelable