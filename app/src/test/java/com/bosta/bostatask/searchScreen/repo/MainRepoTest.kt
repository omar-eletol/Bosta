package com.bosta.bostatask.searchScreen.repo


import com.bosta.bostatask.datasource.repository.RepositoryImp
import com.bosta.bostatask.datasource.service.ApiService
import com.bosta.bostatask.domain.models.CitiesDistrictsResponse
import com.bosta.bostatask.domain.models.CityModel
import com.bosta.bostatask.domain.models.DistrictModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


class MainRepoTest {

    @Mock
    private lateinit var mockApiService: ApiService

    private lateinit var homeRepositoryImp: RepositoryImp

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        homeRepositoryImp = RepositoryImp(mockApiService)
    }

    @Test
    fun testGetCitiesDistrictsApi() {
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
            `when`(mockApiService.getCities()).thenReturn(mockCitiesDistrictsResponse)

            val result = homeRepositoryImp.getCities()

            assertEquals(mockCitiesDistrictsResponse, result)
        }
    }

}

