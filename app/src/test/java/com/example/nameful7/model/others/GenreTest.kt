package com.example.nameful7.model.others

import org.junit.Assert.*
import org.junit.Test

class GenreTest {
    private val genre = Genre("Action", 28)

    @Test
    fun `genre title should match expected value`() {
        assertEquals("Action", genre.name)
    }
    @Test
    fun `genre id should match expected value`() {
        assertEquals(28, genre.id)
    }

    @Test
    fun `genre title not match unexpected values`() {
        assertNotEquals("What", genre.name)
    }
    @Test
    fun `genre id not match unexpected values`() {
        assertNotEquals(35, genre.id)
    }
}
