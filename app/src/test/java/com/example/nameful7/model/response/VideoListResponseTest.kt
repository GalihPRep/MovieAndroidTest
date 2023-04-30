package com.example.nameful7.model.response

import com.example.nameful7.model.others.Video
import org.junit.Assert.*
import org.junit.Test

class VideoListResponseTest {

    private val videoListResponse = VideoListResponse(
        "216",
        listOf(
            Video("video1", "123", false, "1"),
            Video("video2", "456", true, "2")
        )
    )


    @Test
    fun `id should match expected value`() {
        assertEquals("216", videoListResponse.id)
    }
    @Test
    fun `video list should contain expected number of videos`() {
        // then
        assertEquals(2, videoListResponse.results?.size)
    }


    @Test
    fun `video id should not match unexpected value`() {
        assertNotEquals("456", videoListResponse.id)
    }
    @Test
    fun `video list should not contain unexpected number of videos`() {
        assertNotEquals(3, videoListResponse.results?.size)
    }
}
