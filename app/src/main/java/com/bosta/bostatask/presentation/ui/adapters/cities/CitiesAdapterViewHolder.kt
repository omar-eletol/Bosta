package com.bosta.bostatask.presentation.ui.adapters.cities

import androidx.recyclerview.widget.RecyclerView
import com.bosta.bostatask.databinding.CitiesRowItemBinding
import com.bosta.bostatask.domain.models.CityModel


class CitiesAdapterViewHolder(
    private val binding: CitiesRowItemBinding,
    private val onClickListener: CitiesOnClickListener

) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CityModel, selectedItemId: String?) {
        binding.item = item

        binding.isSelected = item.cityId == selectedItemId


        binding.root.setOnClickListener {
            onClickListener.onItemClick(cityItem = item)
        }

        binding.executePendingBindings()
    }
}