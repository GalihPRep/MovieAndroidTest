package com.example.nameful7.repository


import com.example.nameful7.BuildConfig
import com.example.nameful7.api.ApiConfig

class GeneralRepository {
    private val client = ApiConfig.getApiService()

    suspend fun getMovies(page:Int) =
        client.getMovies(BuildConfig.API_KEY, page)

    suspend fun getMoviesByGenre(page: Int, withGenres: String) =
        client.getMoviesByGenre(BuildConfig.API_KEY, page, withGenres)

    suspend fun getMoviesByName(page: Int, query: String) =
        client.getMoviesByName(BuildConfig.API_KEY,page,query)

    suspend fun getGenres() =
        client.getGenres(BuildConfig.API_KEY)

    suspend fun getMovieTrailers(movieId: Int) =
        client.getMovieTrailers(movieId,BuildConfig.API_KEY)

    suspend fun getMovieReviews(movieId: Int, page: Int) =
        client.getMovieReviews(movieId,BuildConfig.API_KEY,page)
}