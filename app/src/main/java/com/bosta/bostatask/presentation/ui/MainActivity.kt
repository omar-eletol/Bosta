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
import com.bosta.bostatask.presentation.core.attachDivider
import com.bosta.bostatask.presentation.ui.adapters.cities.CitiesAdapter
import com.bosta.bostatask.presentation.ui.adapters.cities.CitiesOnClickListener
import com.bosta.bostatask.presentation.ui.states.MainState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityBinding>(R.layout.main_activity),
    CitiesOnClickListener {

    private val viewModel by viewModels<MainViewModel>()
    private val citiesAdapter by lazy { CitiesAdapter(onClickListener = this) }

    @OptIn(FlowPreview::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding.viewModel = this.viewModel
        binding.citiesRecyclerView.adapter = citiesAdapter
        binding.citiesOnClickListener = this
        binding.citiesRecyclerView.attachDivider()



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
                            citiesAdapter.setCitiesList(result.data.data)
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
                viewModel.actions.collect { action ->
                    when (action) {
                        MainActions.FetchCitiesAndDistrictsList -> fetchCitiesDistrictsList()
                        is MainActions.OnItemClicked -> {
                            citiesAdapter.notifyItemChanged(action.adapterPosition)
                        }

                        is MainActions.SearchCities -> {
                            binding.clearTextIcon = action.query.isNotEmpty()
                            searchCities(query = action.query)
                            if (action.query.isEmpty()) {
                                citiesAdapter.removeAllExpandedItems()
                            }
                        }

                        MainActions.ClearSearchText -> TODO()
                    }
                }
            }


        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.search.debounce(200).distinctUntilChanged().filterNotNull()
                    .collect { query ->
                        viewModel.emitAction(
                            action = MainActions.SearchCities(
                                query = query
                            )
                        )
                    }
            }
        }


        viewModel.emitAction(action = MainActions.FetchCitiesAndDistrictsList)

    }

    private fun fetchCitiesDistrictsList() {
        viewModel.fetchCitiesDistrictsList()
    }

    private fun searchCities(query: String) {
        val count = citiesAdapter.searchCitiesReturnCount(query = query)
        binding.isEmpty = count == 0
    }


    override fun onItemClick(cityItem: CityModel, adapterPosition: Int) {
        viewModel.emitAction(
            action = MainActions.OnItemClicked(
                cityItem = cityItem,
                adapterPosition = adapterPosition
            )
        )

    }

    override fun fetchCitiesDistricts() {
        viewModel.search.value = ""
        viewModel.emitAction(action = MainActions.FetchCitiesAndDistrictsList)
    }

}


