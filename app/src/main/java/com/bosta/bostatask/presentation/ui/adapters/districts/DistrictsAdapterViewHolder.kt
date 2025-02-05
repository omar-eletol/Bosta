package com.bosta.bostatask.presentation.ui.adapters.districts

import androidx.recyclerview.widget.RecyclerView
import com.bosta.bostatask.databinding.DistrictsRowItemBinding
import com.bosta.bostatask.domain.models.DistrictModel


class DistrictsAdapterViewHolder(
    private val binding: DistrictsRowItemBinding,

    ) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: DistrictModel) {
        binding.districtsItem = item
    }
}