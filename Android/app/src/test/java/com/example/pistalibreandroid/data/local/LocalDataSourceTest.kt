package com.example.pistalibreandroid.data.local

import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class LocalDataSourceTest {

    private lateinit var localDataSource: LocalDataSource
    private lateinit var preferencesDatabase: PreferencesDatabase
    private val token = "token"

    @Before
    fun setUp() {
        preferencesDatabase = mockk()
        localDataSource = LocalDataSource(preferencesDatabase)
    }

    @Test
    fun `WHEN getToken THEN success`() {
        every { preferencesDatabase.getData(any()) } returns token

        val actual = localDataSource.getToken()

        assertEquals(actual, token)
    }

    @Test
    fun `WHEN setToken THEN success`() {
        every { preferencesDatabase.setData(any(), any()) } returns Unit

        localDataSource.setToken(token)
    }

    @Test
    fun `WHEN getTypeUser THEN success`() {
        every { preferencesDatabase.getData(any()) } returns token

        val actual = localDataSource.getTypeUser()

        assertEquals(actual, token)
    }

    @Test
    fun `WHEN setTypeUser THEN success`() {
        every { preferencesDatabase.setData(any(), any()) } returns Unit

        localDataSource.setTypeUser(token)
    }

}