package com.example.pistalibreandroid.ui.registro.club

import com.example.pistalibreandroid.data.Repository
import com.example.pistalibreandroid.helpers.MainCoroutineRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.testng.Assert
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class SignClubViewModelTest {

    private lateinit var signClubViewModel: SignClubViewModel
    private lateinit var repository: Repository

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        repository = Repository(mockk(), mockk())
        signClubViewModel = SignClubViewModel(repository)
    }

    @Test
    fun emailInvalid() {
        val invalidEmails = listOf("plainaddress", "@missingusername.com", "username@.com.my", "username@.com")

        invalidEmails.forEach { email ->
            signClubViewModel.onSignClubChanged(email = email, password = "validPassword123")
            Assert.assertFalse(signClubViewModel.isSignClubEnable.value)
        }
    }

    @Test
    fun emailValid() {
        val validEmails = listOf("email@example.com", "firstname.lastname@example.com", "email@subdomain.example.com", "firstname+lastname@example.com")

        validEmails.forEach { email ->
            signClubViewModel.onSignClubChanged(email = email, password = "validPassword123")
            Assert.assertTrue(signClubViewModel.isSignClubEnable.value)
        }
    }

    @Test
    fun passwordInvalid() {
        val invalidPasswords = listOf("", "abc", "12345")

        invalidPasswords.forEach { password ->
            signClubViewModel.onSignClubChanged(email = "valid@email.com", password = password)
            Assert.assertFalse(signClubViewModel.isSignClubEnable.value)
        }
    }

    @Test
    fun passwordValid() {
        val validPasswords = listOf("123456", "abcdef", "123456789")

        validPasswords.forEach { password ->
            signClubViewModel.onSignClubChanged(email = "valid@email.com", password = password)
            Assert.assertTrue(signClubViewModel.isSignClubEnable.value)
        }
    }

    @Test
    fun onSignUpSelectedTest() {
        coEvery { repository.singUpClub(any(), any(), any(), any()) } returns Response.success(null)

        signClubViewModel.onSignUpClubSelected()
    }
}