package com.example.nameful7.model.others

import org.junit.Assert.*
import org.junit.Test

class MovieTest {

    private val movie = Movie(
        overview = "Hanabi and Mugi are the perfect couple.",
        originalLanguage = "jp",
        originalTitle = "クズの本懐",
        video = false,
        title = "Scum's Wish",
        genreIds = listOf(1,2,4,8,16),
        posterPath = "https://website.net/images/60/poster/3600.jpg",
        backdropPath = "https://website.net/images/60/backdrop/3600.jpg",
        releaseDate = "2017-01-13",
        popularity = 5.0f,
        voteAverage = 5.0f,
        id = 60,
        adult = true,
        voteCount = 216000
    )


    @Test
    fun `movie overview should match expected value`() {
        assertEquals("Hanabi and Mugi are the perfect couple.", movie.overview)
    }
    @Test
    fun `movie original language should match expected value`() {
        assertEquals("jp", movie.originalLanguage)
    }
    @Test
    fun `movie original title should match expected value`() {
        assertEquals("クズの本懐", movie.originalTitle)
    }
    @Test
    fun `movie video should match expected value`() {
        assertEquals(false, movie.video)
    }
    @Test
    fun `movie title should match expected value`() {
        assertEquals("Scum's Wish", movie.title)
    }
    @Test
    fun `movie genre ids should match expected value`() {
        assertEquals(listOf(1,2,4,8,16), movie.genreIds)
    }
    @Test
    fun `movie poster path should match expected value`() {
        assertEquals("https://website.net/images/60/poster/3600.jpg", movie.posterPath)
    }
    @Test
    fun `movie backdrop path should match expected value`() {
        assertEquals("https://website.net/images/60/backdrop/3600.jpg", movie.backdropPath)
    }
    @Test
    fun `movie release date should match expected value`() {
        assertEquals("2017-01-13", movie.releaseDate)
    }
    @Test
    fun `movie popularity should match expected value`() {
        assertEquals(5.0f, movie.popularity)
    }
    @Test
    fun `movie vote average should match expected value`() {
        assertEquals(5.0f, movie.voteAverage)
    }
    @Test
    fun `movie id should match expected value`() {
        assertEquals(60, movie.id)
    }
    @Test
    fun `movie adult should match expected value`() {
        assertEquals(true, movie.adult)
    }
    @Test
    fun `movie vote count should match expected value`() {
        assertEquals(216000, movie.voteCount)
    }




    @Test
    fun `movie overview should not match unexpected value`() {
        assertNotEquals("Huh?", movie.overview)
    }
    @Test
    fun `movie original language should not match unexpected value`() {
        assertNotEquals("Huh?", movie.originalLanguage)
    }
    @Test
    fun `movie original title should not match unexpected value`() {
        assertNotEquals("Huh?", movie.originalTitle)
    }
    @Test
    fun `movie video should not match unexpected value`() {
        assertNotEquals(true, movie.video)
    }
    @Test
    fun `movie title should not match unexpected value`() {
        assertNotEquals("Huh?",movie.title)
    }
    @Test
    fun `movie genre ids should not match unexpected value`() {
        assertNotEquals(listOf(1,3,9,27,81),movie.genreIds)
    }
    @Test
    fun `movie poster path should not match unexpected value`() {
        assertNotEquals("Huh?",movie.posterPath)
    }
    @Test
    fun `movie backdrop path should not match unexpected value`() {
        assertNotEquals("Huh?",movie.backdropPath)
    }
    @Test
    fun `movie release date should not match unexpected value`() {
        assertNotEquals("2048-09-16",movie.releaseDate)
    }
    @Test
    fun `movie popularity should not match unexpected value`() {
        assertNotEquals(0.0f,movie.popularity)
    }
    @Test
    fun `movie vote average should not match unexpected value`() {
        assertNotEquals(0.0f,movie.voteAverage)
    }
    @Test
    fun `movie id should not match unexpected value`() {
        assertNotEquals(64,movie.id)
    }
    @Test
    fun `movie adult should not match unexpected value`() {
        assertNotEquals(false,movie.adult)
    }
    @Test
    fun `movie vote count should not match unexpected value`() {
        assertNotEquals(2048,movie.voteCount)
    }
}
