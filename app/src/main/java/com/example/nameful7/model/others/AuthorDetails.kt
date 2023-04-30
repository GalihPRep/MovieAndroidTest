package com.example.nameful7.model.others

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuthorDetails (
    @field:SerializedName("name")
    val name: String?,

    @field:SerializedName("username")
    val username: String?,

    @field:SerializedName("avatar_path")
    val avatarPath: String?,

    @field:SerializedName("rating")
    val rating: Float?
): Parcelable