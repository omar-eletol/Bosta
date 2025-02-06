package com.bosta.bostatask.searchScreen.usecases


import com.bosta.bostatask.datasource.repository.RepositoryImp
import com.bosta.bostatask.domain.models.CitiesDistrictsResponse
import com.bosta.bostatask.domain.models.CityModel
import com.bosta.bostatask.domain.models.DistrictModel
import com.bosta.bostatask.domain.usecases.GetCitiesUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GetCitiesUseCaseTest {

    @Mock
    private lateinit var mockRepositoryImp: RepositoryImp

    private lateinit var getCitiesUseCase: GetCitiesUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getCitiesUseCase = GetCitiesUseCase(mockRepositoryImp)
    }

    @Test
    fun testGetCitiesUseCase() {
        runTest {
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
                                districtId = "201",
                                districtName = "San Stefano",
                                zoneName = "Zone C"
                            )
                        )
                    )
                ), message = "Success", success = true
            )
            `when`(mockRepositoryImp.getCities()).thenReturn(mockCitiesDistrictsResponse)

            val result = getCitiesUseCase.invoke()
            assertEquals(mockCitiesDistrictsResponse, result)
        }
    }
}
