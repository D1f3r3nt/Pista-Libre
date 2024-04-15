package com.example.pistalibreandroid.ui.login

import com.example.pistalibreandroid.data.Repository
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.testng.Assert.assertFalse
import org.testng.Assert.assertTrue

@ExtendWith(MockKExtension::class)
class LoginViewModelTest {

    private lateinit var viewModel: LoginViewModel

    @BeforeEach
    fun setup(){
        val mockRepository = mockk<Repository>(relaxed = true)
        viewModel = LoginViewModel(mockRepository)
    }

    @Test
    fun emailInvalid () {
        val invalidEmails = listOf("plainaddress", "@missingusername.com", "username@.com.my", "username@.com", ".username@yahoo.com")

        invalidEmails.forEach { email ->
            viewModel.onLoginChanged(email, "validPass123")
            assertFalse(viewModel.isLoginEnable.value, "Email $email should be rejected")
        }
    }

    @Test
    fun emailValid() {
        val validEmails = listOf("email@example.com", "firstname.lastname@example.com", "email@subdomain.example.com", "firstname+lastname@example.com")

        validEmails.forEach { email ->
            viewModel.onLoginChanged(email, "validPass123")
            assertTrue(viewModel.isLoginEnable.value, "Email $email should be accepted")
        }
    }

    @Test
    fun passwordInvalid() {
        val invalidPasswords = listOf("", "abc", "12345")

        invalidPasswords.forEach { password ->
            viewModel.onLoginChanged("valid@email.com", password)
            assertFalse(viewModel.isLoginEnable.value, "Password '$password' should be rejected because it is too short")
        }
    }

    @Test
    fun passwordValid() {
        val validPasswords = listOf("123456", "abcdef", "123456789")

        validPasswords.forEach { password ->
            viewModel.onLoginChanged("valid@email.com", password)
            assertTrue(viewModel.isLoginEnable.value, "Password '$password' should be accepted")
        }
    }
}