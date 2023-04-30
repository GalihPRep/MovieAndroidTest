package com.example.nameful7.model.response

import com.example.nameful7.model.others.Genre
import org.junit.Assert.*
import org.junit.Test

class GenreListResponseTest {

    private val genres = listOf(
        Genre("Action",28),
        Genre("Adventure",12),
        Genre("Comedy", 35)
    )

    @Test
    fun `genres should match expected values`() {
        val response = GenreListResponse(genres)
        assertNotNull(response.genres)
        assertEquals(genres, response.genres)
    }

    @Test
    fun `genres should be null`() {
        val response = GenreListResponse()
        assertNull(response.genres)
    }

    @Test
    fun `genres should not match unexpected values`() {
        val response = GenreListResponse(genres)
        assertNotEquals(
            listOf(
                Genre("Action",28),
                Genre("Adventure",12),
                Genre("Comedy", 35),
                Genre( "Horror",99)),
            response.genres
        )
    }
}
