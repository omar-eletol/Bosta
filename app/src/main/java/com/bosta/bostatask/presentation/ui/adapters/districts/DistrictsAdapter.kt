package com.bosta.bostatask.presentation.ui.adapters.districts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bosta.bostatask.databinding.DistrictsRowItemBinding
import com.bosta.bostatask.domain.models.DistrictModel


class DistrictsAdapter() :
    ListAdapter<DistrictModel, DistrictsAdapterViewHolder>(DistrictsDataDC()) {
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): DistrictsAdapterViewHolder = DistrictsAdapterViewHolder(
        binding = DistrictsRowItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ),

        )

    override fun onBindViewHolder(holder: DistrictsAdapterViewHolder, position: Int) =
        holder.bind(item = getItem(position))


}