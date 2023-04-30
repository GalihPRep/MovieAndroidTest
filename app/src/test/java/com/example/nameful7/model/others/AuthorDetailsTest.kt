package com.example.nameful7.model.others

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class AuthorDetailsTest {

    private val authorDetails = AuthorDetails(
        "Hanabi Yasuraoka", "hana2048", "/avatar/path", 4.5f
    )

    @Test
    fun `name should match expected values`() {
        assertEquals("Hanabi Yasuraoka", authorDetails.name)
    }
    @Test
    fun `username should match expected values`() {
        assertEquals("hana2048", authorDetails.username)
    }
    @Test
    fun `avatar path should match expected values`() {
        assertEquals("/avatar/path", authorDetails.avatarPath)
    }
    @Test
    fun `rating should match expected values`() {
        assertEquals(4.5f, authorDetails.rating)
    }



    @Test
    fun `name should not match unexpected values`() {
        assertNotEquals("Huh", authorDetails.name)
    }
    @Test
    fun `username should not match unexpected values`() {
        assertNotEquals("Huh", authorDetails.username)
    }
    @Test
    fun `avatar path should not match unexpected values`() {
        assertNotEquals("/avatar", authorDetails.avatarPath)
    }
    @Test
    fun `rating should not match unexpected values`() {
        assertNotEquals(1.5f, authorDetails.rating)
    }
}