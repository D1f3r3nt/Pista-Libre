package com.example.pistalibreandroid.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.runtime.collectAsState
import com.example.pistalibreandroid.data.Repository
import com.example.pistalibreandroid.model.ResponseOk
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.testng.Assert.assertFalse
import org.testng.Assert.assertTrue


@ExperimentalCoroutinesApi
class LoginViewModelTest {

    @RelaxedMockK
    lateinit var repository: Repository
    private lateinit var loginViewModel: LoginViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        loginViewModel = LoginViewModel(repository)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `emailInvalid` () {
        val invalidEmails = listOf("plainaddress", "@missingusername.com", "username@.com.my", "username@.com", ".username@yahoo.com")

        invalidEmails.forEach { email ->
            loginViewModel.onLoginChanged(email, "validPass123")
            assertFalse(loginViewModel.isLoginEnable.value, "Email $email should be rejected")
        }
    }

    @Test
    fun `emailValid`() {
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
}
