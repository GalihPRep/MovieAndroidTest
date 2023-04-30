package com.example.nameful7.model.others

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Genre(
	@field:SerializedName("name")
	val name: String?,
	@field:SerializedName("id")
	val id: Int?
): Parcelable
