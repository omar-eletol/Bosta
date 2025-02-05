package com.bosta.bostatask.presentation.ui.adapters.cities

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.bosta.bostatask.databinding.CitiesRowItemBinding
import com.bosta.bostatask.domain.models.CityModel
import com.bosta.bostatask.presentation.ui.adapters.districts.DistrictsAdapter


class CitiesAdapterViewHolder(
    private val binding: CitiesRowItemBinding,
    private val onClickListener: CitiesOnClickListener,
    private val expandedItems: MutableSet<String>,
) : RecyclerView.ViewHolder(binding.root) {

    private val districtsAdapter by lazy { DistrictsAdapter() }

    @SuppressLint("ClickableViewAccessibility")
    fun bind(
        item: CityModel,
        searchQuery: String,
    ) {
        val isExpanded = expandedItems.contains(item.cityId)
        binding.item = item
        binding.disrictsRecyclerView.adapter = districtsAdapter

        binding.root.setOnClickListener {
            if (isExpanded) {
                expandedItems.remove(item.cityId)
            } else {
                expandedItems.add(item.cityId ?: "")
            }
            onClickListener.onItemClick(cityItem = item, absoluteAdapterPosition)
        }

        binding.isSelected = isExpanded

        districtsAdapter.setDistrictsList(null)
        districtsAdapter.setDistrictsList(item.districts)
        if (searchQuery != "") {
            districtsAdapter.searchDistrictsReturnCount(searchQuery)
        } else {
            districtsAdapter.setDistrictsList(item.districts)
        }
        binding.executePendingBindings()
    }
}


