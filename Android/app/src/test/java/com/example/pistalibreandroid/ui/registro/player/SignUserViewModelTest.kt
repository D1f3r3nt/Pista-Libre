package com.example.pistalibreandroid.ui.registro.player

import com.example.pistalibreandroid.data.Repository
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.testng.Assert.assertFalse
import org.testng.Assert.assertTrue

@ExtendWith(MockKExtension::class)
class SignUserViewModelTest {



    /*private lateinit var viewModel: SignUserViewModel
    private lateinit var mockRepository: Repository

    @BeforeEach
    fun setup() {
        mockRepository = mockk(relaxed = true)
        viewModel = SignUserViewModel(mockRepository)
    }

    @Test
    fun emailTest() {
        val validEmails = listOf("email@example.com", "firstname.lastname@example.com", "email@subdomain.example.com")
        val invalidEmails = listOf("plainaddress", "@missingusername.com", "username@.com.my", "username@.com", ".username@yahoo.com")

        validEmails.forEach { email ->
            viewModel.onUserSignChanged(email = email, password = "password123")
            assertTrue(viewModel.isUserSignEnable.value, "Email $email should be accepted")
        }

        invalidEmails.forEach { email ->
            viewModel.onUserSignChanged(email = email, password = "password123")
            assertFalse(viewModel.isUserSignEnable.value, "Email $email should be rejected")
        }
    }

    @Test
    fun passwordTest() {
        val invalidPasswords = listOf("", "a", "ab", "abc", "abcd", "abcde")

        invalidPasswords.forEach { password ->
            viewModel.onUserSignChanged(email = "test@example.com", password = password)
            assertFalse(viewModel.isUserSignEnable.value, "Password '$password' should be rejected because it is too short")
        }

        viewModel.onUserSignChanged(email = "test@example.com", password = "123456")
        assertTrue(viewModel.isUserSignEnable.value, "Password '123456' should be accepted")
    }

    @Test
    fun buttonTest() {
        viewModel.onUserSignChanged(email = "test@", password = "123456")
        assertFalse(viewModel.isUserSignEnable.value, "Button should be disabled with invalid email")

        viewModel.onUserSignChanged(email = "test@example.com", password = "12345")
        assertFalse(viewModel.isUserSignEnable.value, "Button should be disabled with short password")

        viewModel.onUserSignChanged(email = "test@example.com", password = "123456")
        assertTrue(viewModel.isUserSignEnable.value, "Button should be enabled with valid email and password")
    }*/
}