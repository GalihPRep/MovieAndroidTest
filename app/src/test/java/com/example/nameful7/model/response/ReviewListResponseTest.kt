package com.example.nameful7.model.response

import com.example.nameful7.model.others.AuthorDetails
import com.example.nameful7.model.others.Review
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class ReviewListResponseTest {
    private val reviewListResponse = ReviewListResponse(
        123,
        1,
        mutableListOf(
            Review(
                author = "Hanabi Yasuraoka",
                authorDetails = AuthorDetails(
                    "Hanabi Yasuraoka",
                    "hana2048",
                    "/avatar/path",
                    4.5f
                ),
                content = "Okay that was beautiful.",
                createdAt = null,
                id = "216000",
                updatedAt = null,
                url = "https://example.com/movie/review/216000"
            )
        ),
        10,
        100
    )

    @Test
    fun `id should match expected value`() {
        assertEquals(123, reviewListResponse.id)
    }
    @Test
    fun `page should match expected value`() {
        assertEquals(1, reviewListResponse.page)
    }
    @Test
    fun `results should match expected value`() {
        assertEquals(1, reviewListResponse.results.size)
    }
    @Test
    fun `total pages should match expected value`() {
        assertEquals(10, reviewListResponse.totalPages)
    }
    @Test
    fun `total results should match expected value`() {
        assertEquals(100, reviewListResponse.totalResults)
    }



    @Test
    fun `id should not match unexpected value`() {
        assertNotEquals(12, reviewListResponse.id)
    }
    @Test
    fun `page should not match unexpected value`() {
        assertNotEquals(2, reviewListResponse.page)
    }
    @Test
    fun `results should not match unexpected value`() {
        assertNotEquals(2, reviewListResponse.results.size)
    }
    @Test
    fun `total pages should not match unexpected value`() {
        assertNotEquals(1, reviewListResponse.totalPages)
    }
    @Test
    fun `total results should not match unexpected value`() {
        assertNotEquals(10, reviewListResponse.totalResults)
    }
}
