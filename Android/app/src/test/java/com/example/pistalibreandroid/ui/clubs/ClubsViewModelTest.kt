package com.example.pistalibreandroid.ui.clubs

import com.example.pistalibreandroid.data.Repository
import com.example.pistalibreandroid.helpers.MainCoroutineRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.testng.Assert.assertEquals
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class ClubsViewModelTest {

    private lateinit var clubsViewModel: ClubsViewModel
    private lateinit var repository: Repository
    
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        repository = Repository(mockk(), mockk())
        clubsViewModel = ClubsViewModel(repository)
    }

    @Test
    fun mainRequest() {
        coEvery { repository.getAllClubs() } returns Response.success(listOf())
        
        clubsViewModel.getAllClubs()

        assertEquals(clubsViewModel.clubsList.value.count(), 0)
    }
}