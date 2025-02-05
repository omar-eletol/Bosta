package com.bosta.bostatask.presentation.ui.adapters.cities

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bosta.bostatask.databinding.CitiesRowItemBinding
import com.bosta.bostatask.domain.models.CityModel
import androidx.recyclerview.widget.ListAdapter


class CitiesAdapter(val onClickListener: CitiesOnClickListener) :
    ListAdapter<CityModel, CitiesAdapterViewHolder>(CitiesDataDC()) {
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): CitiesAdapterViewHolder = CitiesAdapterViewHolder(
        binding = CitiesRowItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ), onClickListener = onClickListener

    )

    override fun onBindViewHolder(holder: CitiesAdapterViewHolder, position: Int) =
        holder.bind(item = getItem(position), selectedItemId = selectedItemId)


    fun setSelectedItemId(id: String) {
        if (selectedItemId == id) return
        selectedItemId = id
        notifyDataSetChanged()
    }

    private var selectedItemId: String? = null

}