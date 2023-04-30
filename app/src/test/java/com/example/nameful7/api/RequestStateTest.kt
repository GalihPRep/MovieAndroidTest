package com.example.nameful7.api

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class RequestStateTest {

    @Test
    fun `success state should contain data`() {
        // Given
        val data = "test"

        // When
        val state = RequestState.Success(data)

        // Then
        assertTrue(state is RequestState.Success)
        assertEquals(data, state.data)
    }

    @Test
    fun `error state should contain error message`() {
        // Given
        val errorMessage = "Error message"

        // When
        val state = RequestState.Error(errorMessage)

        // Then
        assertTrue(state is RequestState.Error)
        assertEquals(errorMessage, state.message)
    }

    @Test
    fun `loading state should not contain data`() {
        // When
        val state = RequestState.Loading

        // Then
        assertTrue(state is RequestState.Loading)
    }

}
