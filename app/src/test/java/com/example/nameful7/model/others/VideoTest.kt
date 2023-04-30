package com.example.nameful7.model.others

import org.junit.Assert.*
import org.junit.Test

class VideoTest {

    private val video = Video(
        name = "Official Trailer",
        key = "abc123",
        official = true,
        id = "video_1"
    )

    @Test
    fun `video name should match expected values`() {
        assertEquals("Official Trailer", video.name)
    }
    @Test
    fun `video key should match expected values`() {
        assertEquals("abc123", video.key)
    }
    @Test
    fun `video official should match expected values`() {
        assertEquals(true, video.official)
    }
    @Test
    fun `video id should match expected values`() {
        assertEquals("video_1", video.id)
    }




    @Test
    fun `video name should not match unexpected value`() {
        assertNotEquals("Trailer", video.name)
    }
    @Test
    fun `video key should not match unexpected value`() {
        assertNotEquals("def456", video.key)
    }
    @Test
    fun `video official should not match unexpected value`() {
        assertNotEquals(false, video.official)
    }
    @Test
    fun `video id should not match unexpected value`() {
        assertNotEquals("video_2", video.id)
    }

}
