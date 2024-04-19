package com.example.pistalibreandroid.ui.login

import com.example.pistalibreandroid.data.Repository
import com.example.pistalibreandroid.helpers.MainCoroutineRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.testng.Assert.assertFalse
import org.testng.Assert.assertTrue
import retrofit2.Response


@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var repository: Repository

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        repository = Repository(mockk(), mockk())
        loginViewModel = LoginViewModel(repository)
    }

    @Test
    fun emailInvalid() {
        val invalidEmails = listOf("plainaddress", "@missingusername.com", "username@.com.my", "username@.com")

        invalidEmails.forEach { email ->
            loginViewModel.onLoginChanged(email, "validPass123")
            assertFalse(loginViewModel.isLoginEnable.value)
        }
    }

    @Test
    fun emailValid() {
        val validEmails = listOf("email@example.com", "firstname.lastname@example.com", "email@subdomain.example.com", "firstname+lastname@example.com")

        validEmails.forEach { email ->
            loginViewModel.onLoginChanged(email, "validPass123")
            assertTrue(loginViewModel.isLoginEnable.value, "Email $email should be accepted")
        }
    }

    @Test
    fun passwordInvalid() {
        val invalidPasswords = listOf("", "abc", "12345")

        invalidPasswords.forEach { password ->
            loginViewModel.onLoginChanged("valid@email.com", password)
            assertFalse(loginViewModel.isLoginEnable.value, "Password '$password' should be rejected because it is too short")
        }
    }

    @Test
    fun passwordValid() {
        val validPasswords = listOf("123456", "abcdef", "123456789")

        validPasswords.forEach { password ->
            loginViewModel.onLoginChanged("valid@email.com", password)
            assertTrue(loginViewModel.isLoginEnable.value, "Password '$password' should be accepted")
        }
    }

    @Test
    fun onLoginSelectedTest() {
        val token: String = "vdfvdfv"
        
        coEvery { repository.login(any(), any()) } returns Response.success(token)
        
        loginViewModel.onLoginSelected()
    }
}
