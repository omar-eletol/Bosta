package com.bosta.bostatask.searchScreen.viewmodel

import app.cash.turbine.test
import com.bosta.bostatask.domain.models.CitiesDistrictsResponse
import com.bosta.bostatask.domain.models.CityModel
import com.bosta.bostatask.domain.models.DistrictModel
import com.bosta.bostatask.domain.usecases.GetCitiesUseCase
import com.bosta.bostatask.domain.usecases.GetDistrictsUseCase
import com.bosta.bostatask.presentation.ui.MainActions
import com.bosta.bostatask.presentation.ui.MainViewModel
import com.bosta.bostatask.presentation.ui.states.MainState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class MainViewModelTest {

    private lateinit var getCitiesUseCase: GetCitiesUseCase
    private lateinit var getDistrictsUseCase: GetDistrictsUseCase

    private lateinit var mainViewModel: MainViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        getCitiesUseCase = mock(GetCitiesUseCase::class.java)
        getDistrictsUseCase = mock(GetDistrictsUseCase::class.java)

        Dispatchers.setMain(testDispatcher)
        mainViewModel = MainViewModel(getCitiesUseCase, getDistrictsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `fetchCitiesDistrictsList emits Loading then Success when API returns data`() = runTest {
        val mockCitiesDistrictsResponse = CitiesDistrictsResponse(
            data = listOf(
                CityModel(
                    cityId = "1", cityName = "Cairo", districts = listOf(
                        DistrictModel(
                            districtId = "101", districtName = "Nasr City", zoneName = "Zone A"
                        ), DistrictModel(
                            districtId = "102", districtName = "Heliopolis", zoneName = "Zone B"
                        )
                    )
                ), CityModel(
                    cityId = "2", cityName = "Alexandria", districts = listOf(
                        DistrictModel(
                            districtId = "201", districtName = "San Stefano", zoneName = "Zone C"
                        )
                    )
                )
            ), message = "Success", success = true
        )
        val mockResponse = MainState.Success(data = mockCitiesDistrictsResponse)
        `when`(getCitiesUseCase.invoke()).thenReturn(mockCitiesDistrictsResponse)

        mainViewModel.fetchCitiesDistrictsList()

        mainViewModel.citiesState.test {
            val secondState = awaitItem()
            assertEquals(mockResponse, secondState)
            cancelAndConsumeRemainingEvents()
        }
    }

//    @Test
//    fun `fetchCitiesDistrictsList emits Loading then Empty when API returns empty list`() =
//        runTest {
//            `when`(getCitiesUseCase.invoke()).thenReturn(
//                CitiesDistrictsResponse(
//                    data = null, success = null, message = null
//                )
//            )
//            mainViewModel.fetchCitiesDistrictsList()
//            mainViewModel.citiesState.test {
//                assertEquals(MainState.Empty, awaitItem())
//                cancelAndConsumeRemainingEvents()
//            }
//        }

    //    @Test
//    fun `fetchCitiesDistrictsList emits Loading then Error when API throws exception`() = runTest {
//        val exception = RuntimeException("Network error")
//        `when`(getCitiesUseCase.invoke()).thenThrow(exception)
//
//        mainViewModel.fetchCitiesDistrictsList()
//
//        mainViewModel.citiesState.test {
//            val errorState = awaitItem() as MainState.Error
//            assertEquals(exception, errorState.throwable)
//            cancelAndConsumeRemainingEvents()
//        }
//    }
//
    @Test
    fun `emitAction sends correct action to channel`() = runTest {
        val testAction = MainActions.FetchCitiesAndDistrictsList

        mainViewModel.emitAction(testAction)

        mainViewModel.actions.test {
            assertEquals(testAction, awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }
}
