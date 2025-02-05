package com.bosta.bostatask.presentation.ui.adapters.cities

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bosta.bostatask.databinding.CitiesRowItemBinding
import com.bosta.bostatask.domain.models.CityModel
import androidx.recyclerview.widget.ListAdapter


class CitiesAdapter(
    val onClickListener: CitiesOnClickListener,
) : ListAdapter<CityModel, CitiesAdapterViewHolder>(CitiesDataDC()) {


    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): CitiesAdapterViewHolder = CitiesAdapterViewHolder(
        binding = CitiesRowItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ), onClickListener = onClickListener, expandedItems = expandedItems
    )

    override fun onBindViewHolder(holder: CitiesAdapterViewHolder, position: Int) {
        holder.bind(
            item = getItem(position), searchQuery = searchQuery
        )
    }

    private val expandedItems = mutableSetOf<String>()
    fun removeAllExpandedItems() {
        expandedItems.clear()
    }

    private fun addAllIdsAfterSearch(citiesList: List<CityModel>){
        for (cityItem in citiesList){
            expandedItems.add(cityItem.cityId ?: "")
        }
    }


    private var searchQuery: String = ""
    private val originalCitiesList = mutableListOf<CityModel>()

    fun searchCitiesReturnCount(query: String): Int {
        searchQuery = query
        val result = if (query.isNotEmpty()) originalCitiesList.filter { city ->
            city.cityName?.contains(query, true) ?: false || city.districts?.any { district ->
                district.districtName?.contains(query, true) ?: false
            } ?: false
        } else originalCitiesList

        submitList(result)
        addAllIdsAfterSearch(citiesList = result)

        notifyDataSetChanged()
        return result.size
    }


    fun setCitiesList(data: List<CityModel>?) {
        originalCitiesList.clear()
        if (data != null) {
            originalCitiesList.addAll(data)
        }
        submitList(data)

    }

}