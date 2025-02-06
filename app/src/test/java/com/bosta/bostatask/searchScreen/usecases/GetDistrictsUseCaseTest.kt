package com.bosta.bostatask.searchScreen.usecases


import com.bosta.bostatask.domain.models.CityModel
import com.bosta.bostatask.domain.models.DistrictModel
import com.bosta.bostatask.domain.usecases.GetDistrictsUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class GetDistrictsUseCaseTest {


    private lateinit var getDistrictsUseCase: GetDistrictsUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getDistrictsUseCase = GetDistrictsUseCase()
    }

    @Test
    fun testGetDistrictsUseCase() {
        runTest {
            val mockCityModel = CityModel(
                cityId = "1", cityName = "Cairo", districts = listOf(
                    DistrictModel(
                        districtId = "101", districtName = "Nasr City", zoneName = "Zone A"
                    ), DistrictModel(
                        districtId = "102", districtName = "Heliopolis", zoneName = "Zone B"
                    ), DistrictModel(
                        districtId = "103", districtName = "Nasr City3", zoneName = "Zone c"
                    )
                )
            )

            val expectedDistricts = mockCityModel.districts
            val result = getDistrictsUseCase.invoke(mockCityModel)
            assertEquals(expectedDistricts, result)
        }
    }
}
