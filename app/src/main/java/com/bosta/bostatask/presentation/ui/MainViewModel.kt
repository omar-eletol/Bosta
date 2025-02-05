package com.bosta.bostatask.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bosta.bostatask.domain.models.CityModel
import com.bosta.bostatask.domain.usecases.GetCitiesUseCase
import com.bosta.bostatask.domain.usecases.GetDistrictsUseCase
import com.bosta.bostatask.presentation.ui.states.DistrictsState
import com.bosta.bostatask.presentation.ui.states.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCitiesUseCase: GetCitiesUseCase,
    private val getDistrictsUseCase: GetDistrictsUseCase
) : ViewModel() {

    private val _actions = Channel<MainActions>()
    val actions = _actions.receiveAsFlow()

    fun emitAction(action: MainActions) = viewModelScope.launch(Dispatchers.IO) {
        _actions.send(action)
    }

    private val _citiesState = MutableStateFlow<MainState>(MainState.Idle)
    val citiesState get() = _citiesState.asStateFlow()

    fun fetchCitiesDistrictsList() = viewModelScope.launch(Dispatchers.IO) {
        _citiesState.emit(MainState.Loading)
        _citiesState.emit(
            try {
                val response = getCitiesUseCase.invoke()
                if (response.data?.isEmpty() == true) MainState.Empty
                else MainState.Success(data = response)
            } catch (t: Throwable) {
                MainState.Error(
                    throwable = t,
                    errorBody = if (t is HttpException) t.response()?.errorBody()
                        ?.string() else null
                )
            }
        )
    }


    private val _districtsState = MutableStateFlow<DistrictsState>(DistrictsState.Idle)
    val districtsState get() = _districtsState.asStateFlow()

    fun getDistrictsList(cityItem: CityModel?) = viewModelScope.launch(Dispatchers.IO) {
        _districtsState.emit(DistrictsState.Loading)
        _districtsState.emit(
            try {
                val districtList = getDistrictsUseCase.invoke(cityItem = cityItem)
                if (districtList?.isEmpty() == true) DistrictsState.Empty
                else DistrictsState.Success(data = districtList)
            } catch (t: Throwable) {
                DistrictsState.Error(
                    throwable = t, errorBody = "something went wrong"
                )
            }
        )
    }


}