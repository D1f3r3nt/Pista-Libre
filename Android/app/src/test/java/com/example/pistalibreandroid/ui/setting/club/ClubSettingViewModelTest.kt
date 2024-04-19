package com.example.pistalibreandroid.ui.setting.club

import com.example.pistalibreandroid.data.Repository
import com.example.pistalibreandroid.helpers.MainCoroutineRule
import com.example.pistalibreandroid.model.setting.club.CourtList
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
class ClubSettingViewModelTest {

    private lateinit var clubSettingViewModel: ClubSettingViewModel
    private lateinit var repository: Repository
    
    private val exampleCourt: CourtList = CourtList(1, true, 1.0F)

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        repository = Repository(mockk(), mockk())
        clubSettingViewModel = ClubSettingViewModel(repository)
    }

    @Test
    fun nameInvalid() {
        val invalidNames = listOf("", "m")
        
        invalidNames.forEach { name ->
            clubSettingViewModel.onConfigChanged(name, "xxx")
            Assert.assertFalse(clubSettingViewModel.isConfigEnable.value)
        }
    }

    @Test
    fun nameValid() {
        val validNames = listOf("msantisteban", "jalvarez")

        clubSettingViewModel.addNewCourt()
        
        validNames.forEach { name ->
            clubSettingViewModel.onConfigChanged(name, "xxx")
            Assert.assertTrue(clubSettingViewModel.isConfigEnable.value)
        }
    }

    @Test
    fun directionInvalid() {
        val invalidDirections = listOf("", "a")

        invalidDirections.forEach { direction ->
            clubSettingViewModel.onConfigChanged("xxx", direction)
            Assert.assertFalse(clubSettingViewModel.isConfigEnable.value)
        }
    }

    @Test
    fun directionValid() {
        val validDirections = listOf("Marc Santisteban", "Juan Alvarez")

        clubSettingViewModel.addNewCourt()
        
        validDirections.forEach { direction ->
            clubSettingViewModel.onConfigChanged("xxx", direction)
            Assert.assertTrue(clubSettingViewModel.isConfigEnable.value)
        }
    }
    
    @Test
    fun addNewCourtCheck() {
        clubSettingViewModel.addNewCourt()
        
        assertEquals(clubSettingViewModel.courts.value.count(), 1)
    }

    @Test
    fun onCourtChangeCheck() {
        val number = 10
        val indoor = false
        val price = 10.0F
        
        clubSettingViewModel.addNewCourt()
        
        clubSettingViewModel.onCourtChange(0, number, indoor, price)

        val first = clubSettingViewModel.courts.value.get(0)
        
        assertEquals(first.number, number)
        assertEquals(first.indoor, indoor)
        assertEquals(first.price, price)
    }
    
    @Test
    fun removeCourtCheck() {
        clubSettingViewModel.addNewCourt()
        clubSettingViewModel.addNewCourt()
        clubSettingViewModel.addNewCourt()
        
        clubSettingViewModel.removeCourt(0)
        
        assertEquals(clubSettingViewModel.courts.value.count(), 2)
    }
    
    @Test
    fun postConfigTest() {
        coEvery { repository.configClub(any(), any(), any()) } returns Response.success(Unit)

        clubSettingViewModel.postConfig()
    }
}