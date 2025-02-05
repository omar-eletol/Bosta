package com.bosta.bostatask.presentation.ui


import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bosta.bostatask.R
import com.bosta.bostatask.databinding.MainActivityBinding
import com.bosta.bostatask.domain.models.CityModel
import com.bosta.bostatask.presentation.core.BaseActivity
import com.bosta.bostatask.presentation.ui.adapters.cities.CitiesAdapter
import com.bosta.bostatask.presentation.ui.adapters.cities.CitiesOnClickListener
import com.bosta.bostatask.presentation.ui.adapters.districts.DistrictsAdapter
import com.bosta.bostatask.presentation.ui.states.DistrictsState
import com.bosta.bostatask.presentation.ui.states.MainState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityBinding>(R.layout.main_activity),
    CitiesOnClickListener {

    private val viewModel by viewModels<MainViewModel>()
    private val citiesAdapter by lazy { CitiesAdapter(onClickListener = this) }
    private val districtsAdapter by lazy { DistrictsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding.citiesRecyclerView.adapter = citiesAdapter
        binding.districtsRecyclerView.adapter = districtsAdapter
        binding.citiesOnClickListener = this



        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.citiesState.collect { result ->
                    binding.isLoading = result == MainState.Loading

                    when (result) {
                        is MainState.Loading -> {
                            binding.swipeRefresher.alpha = 0f
                            binding.hasError = false
                        }

                        is MainState.Error -> {
                            binding.hasError = true
                        }

                        is MainState.Success -> {
                            binding.swipeRefresher.alpha = 1f
                            binding.hasError = false

                            citiesAdapter.submitList(result.data.data)
                            viewModel.emitAction(action = MainActions.OnItemClicked(cityItem = result.data.data?.first()))
                        }

                        MainState.Empty -> {
                            binding.hasError = true

                        }

                        MainState.Idle -> {
                            binding.hasError = false

                        }
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.districtsState.collect { result ->
                    when (result) {
                        is DistrictsState.Loading -> {
                            binding.hasError = false
                        }

                        is DistrictsState.Error -> {
                            binding.hasError = true

                        }

                        is DistrictsState.Success -> {
                            binding.hasError = false
                            districtsAdapter.submitList(result.data)
                        }

                        DistrictsState.Empty -> {
                            binding.hasError = true

                        }

                        DistrictsState.Idle -> {
                            binding.hasError = false

                        }
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.actions.collect { action ->
                    when (action) {
                        MainActions.FetchCitiesAndDistrictsList -> fetchCitiesDistrictsList()
                        is MainActions.OnItemClicked -> {
                            citiesAdapter.setSelectedItemId(id = action.cityItem?.cityId ?: "")
                            getDistrictsList(action.cityItem)
                        }
                    }
                }
            }


        }

        viewModel.emitAction(action = MainActions.FetchCitiesAndDistrictsList)


    }

    private fun fetchCitiesDistrictsList() {
        viewModel.fetchCitiesDistrictsList()
    }

    private fun getDistrictsList(cityItem: CityModel?) {
        viewModel.getDistrictsList(cityItem = cityItem)
    }

    override fun onItemClick(cityItem: CityModel) {
        viewModel.emitAction(action = MainActions.OnItemClicked(cityItem = cityItem))

    }

    override fun fetchCitiesDistricts() {
        viewModel.emitAction(action = MainActions.FetchCitiesAndDistrictsList)
    }

}


