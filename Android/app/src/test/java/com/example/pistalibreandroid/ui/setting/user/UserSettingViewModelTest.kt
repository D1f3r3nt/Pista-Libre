package com.example.pistalibreandroid.ui.setting.user

import com.example.pistalibreandroid.data.Repository
import com.example.pistalibreandroid.helpers.MainCoroutineRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.testng.Assert
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class UserSettingViewModelTest {

    private lateinit var userSettingViewModel: UserSettingViewModel
    private lateinit var repository: Repository

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        repository = Repository(mockk(), mockk())
        userSettingViewModel = UserSettingViewModel(repository)
    }

    @Test
    fun usernameInvalid() {
        val invalidUsernames = listOf("", "m")

        invalidUsernames.forEach { username ->
            userSettingViewModel.onConfigChanged("xxx", username)
            Assert.assertFalse(userSettingViewModel.isConfigEnable.value)
        }
    }

    @Test
    fun usernameValid() {
        val validUsernames = listOf("msantisteban", "jalvarez")

        validUsernames.forEach { username ->
            userSettingViewModel.onConfigChanged("xxx", username)
            Assert.assertTrue(userSettingViewModel.isConfigEnable.value)
        }
    }

    @Test
    fun fullnameInvalid() {
        val invalidFullnames = listOf("", "a")

        invalidFullnames.forEach { fullname ->
            userSettingViewModel.onConfigChanged(fullname, "xxx")
            Assert.assertFalse(userSettingViewModel.isConfigEnable.value)
        }
    }

    @Test
    fun fullnameValid() {
        val validFullnames = listOf("Marc Santisteban", "Juan Alvarez")

        validFullnames.forEach { fullname ->
            userSettingViewModel.onConfigChanged(fullname, "xxx")
            Assert.assertTrue(userSettingViewModel.isConfigEnable.value)
        }
    }
    
    @Test
    fun changeSidePlay() {
        val newSide = 1
        
        userSettingViewModel.changeSidePlay(newSide)
        
        assertEquals(userSettingViewModel.sidePlay.value, newSide)
    }

    @Test
    fun postConfigTest() {
        coEvery { repository.configUser(any(), any(), any()) } returns Response.success(Unit)

        userSettingViewModel.postConfig()
    }
}