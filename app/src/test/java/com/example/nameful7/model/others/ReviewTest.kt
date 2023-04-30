package com.example.nameful7.model.others

import org.junit.Test
import org.junit.Assert.*

class ReviewTest {

    private val review = Review(
        author = "Hanabi Yasuraoka",
        authorDetails = AuthorDetails(
            "Hanabi Yasuraoka", "hana2048", "/avatar/path", 4.5f
        ),
        content = "Okay that was beautiful.",
        createdAt = null,
        id = "216000",
        updatedAt = null,
        url = "https://example.com/movie/review/216000"
    )

    @Test
    fun `author should match expected values`() {
        assertEquals("Hanabi Yasuraoka", review.author)
    }
    @Test
    fun `author details should match expected values`() {
        assertEquals(
            AuthorDetails(
                "Hanabi Yasuraoka", "hana2048", "/avatar/path", 4.5f
            ),
            review.authorDetails)
    }
    @Test
    fun `content should match expected values`() {
        assertEquals("Okay that was beautiful.", review.content)
    }
    @Test
    fun `id should match expected values`() {
        assertEquals("216000", review.id)
    }
    @Test
    fun `url should match expected values`() {
        assertEquals("https://example.com/movie/review/216000", review.url)
    }



    @Test
    fun `author should not match unexpected values`() {
        assertNotEquals("Mugi Awaya", review.author)
    }
    @Test
    fun `author details should not match unexpected values`() {
        assertNotEquals(
            AuthorDetails(
                "Mugi Awaya", "mug1", "/avatar/path", 4.5f
            ),
            review.authorDetails)
    }
    @Test
    fun `content should not match unexpected values`() {
        assertNotEquals("This movie was terrible!", review.content)
    }
    @Test
    fun `id should not match unexpected values`() {
        assertNotEquals("456", review.id)
    }
    @Test
    fun `url should not match unexpected values`() {
        assertNotEquals("https://example.com/movie/review/456", review.url)
    }

}
