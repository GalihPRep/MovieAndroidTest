package com.example.nameful7.api

import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfigTest {

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `getApiService should return ApiService instance`() {
        // Arrange
        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(ApiService::class.java)

        // Act
        val result = ApiConfig.getApiService()

        // Assert
        assertNotNull(result)
        assertEquals(apiService.javaClass, result.javaClass)
    }

    // @Test(expected = IllegalArgumentException::class)
    @Test
    fun `getApiService should throw an exception when base url is invalid`() {
        // Arrange
        val baseUrlInvalid = "what?"

        // Act and Assert
        // ApiConfig.getApiService(baseUrlInvalid)
        assertThrows<IllegalArgumentException> { ApiConfig.getApiService(baseUrlInvalid) }
    }
}
