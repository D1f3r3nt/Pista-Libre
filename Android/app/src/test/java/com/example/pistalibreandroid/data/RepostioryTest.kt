package com.example.pistalibreandroid.data

import com.example.pistalibreandroid.data.network.NetworkDataSource
import com.example.pistalibreandroid.data.network.response.ClubsListResponse
import com.example.pistalibreandroid.helpers.fake.FakeLocalDataSource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class RepostioryTest {

    private lateinit var localDataSource: FakeLocalDataSource
    private lateinit var remoteDataSource: NetworkDataSource

    @Before
    fun setUp() {
        localDataSource = FakeLocalDataSource()
        remoteDataSource = mockk()
    }

    @Test
    fun `WHEN do login THEN get from network`() = runTest {
        val repository = Repository(localDataSource, remoteDataSource)
        val token = "token"

        coEvery { remoteDataSource.login(any(), any()) } returns Response.success(token)

        val response = repository.login("", "")

        assertTrue(response.isSuccessful)
    }

    @Test
    fun `WHEN do singUpClub THEN get from network`() = runTest {
        val repository = Repository(localDataSource, remoteDataSource)

        coEvery { remoteDataSource.singUpClub(any(), any(), any(), any()) } returns Response.success(Unit)

        val response = repository.singUpClub("", "", "", "")

        assertTrue(response.isSuccessful)
    }

    @Test
    fun `WHEN do singUpUser THEN get from network`() = runTest {
        val repository = Repository(localDataSource, remoteDataSource)

        coEvery { remoteDataSource.singUpUser(any(), any(), any(), any()) } returns Response.success(Unit)

        val response = repository.singUpUser("", "", "", "")

        assertTrue(response.isSuccessful)
    }

    @Test
    fun `WHEN do configUser THEN get from network`() = runTest {
        val repository = Repository(localDataSource, remoteDataSource)

        coEvery { remoteDataSource.configUser(any(), any(), any(), any()) } returns Response.success(Unit)

        val response = repository.configUser("", "", "")

        assertTrue(response.isSuccessful)
    }

    @Test
    fun `WHEN do getAllClubs THEN get from network`() = runTest {
        val repository = Repository(localDataSource, remoteDataSource)
        val clubsListResponse = ClubsListResponse(1, "name", "location")
        
        coEvery { remoteDataSource.getAllClubs(any()) } returns Response.success(listOf(clubsListResponse))

        val response = repository.getAllClubs()

        assertTrue(response.isSuccessful)
        assertEquals(response.body()?.count(), 1)
    }

    @Test
    fun `WHEN do configClub THEN get from network`() = runTest {
        val repository = Repository(localDataSource, remoteDataSource)

        coEvery { remoteDataSource.configClub(any(), any(), any(), any()) } returns Response.success(Unit)

        val response = repository.configClub("", "", emptyList())

        assertTrue(response.isSuccessful)
    }

    @Test
    fun `WHEN do getToken and setToken THEN get from local`() = runTest {
        val repository = Repository(localDataSource, remoteDataSource)
        val token = "xxx"
        
        localDataSource.setToken(token)
        

        val response = repository.getToken()

        assertEquals(response, token)
    }
}