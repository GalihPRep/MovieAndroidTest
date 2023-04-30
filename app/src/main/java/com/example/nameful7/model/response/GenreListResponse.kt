package com.example.nameful7.model.response

import com.example.nameful7.model.others.Genre

data class GenreListResponse(
	val genres: List<Genre>? = null
)
