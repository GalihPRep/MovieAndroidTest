package com.example.nameful7.api


import com.example.nameful7.model.response.GenreListResponse
import com.example.nameful7.model.response.MovieListResponse
import com.example.nameful7.model.response.ReviewListResponse
import com.example.nameful7.model.response.VideoListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
    @GET("movie/popular")
    suspend fun getMovies(
        @Query("api_key") key: String?,
        @Query("page") page: Int?
    ): Response<MovieListResponse>

    @GET("discover/movie")
    suspend fun getMoviesByGenre(
        @Query("api_key") key: String?,
        @Query("page") page: Int?,
        @Query("with_genres") withGenres: String?
    ): Response<MovieListResponse>

    @GET("search/movie")
    suspend fun getMoviesByName(
        @Query("api_key") key: String?,
        @Query("page") page: Int?,
        @Query("query") query: String?,
    ): Response<MovieListResponse>

    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") key: String?,
    ): Response<GenreListResponse>

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieTrailers(
        @Path("movie_id") movieId: Int?,
        @Query("api_key") key: String?,
    ): Response<VideoListResponse>

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Int?,
        @Query("api_key") key: String?,
        @Query("page") page: Int?
    ): Response<ReviewListResponse>
}