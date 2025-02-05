package com.bosta.bostatask.presentation.ui.adapters.districts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
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


    fun searchDistrictsReturnCount(query: String): Int {
        val result = if (query.isNotEmpty()) originalCitiesList.filter {
            it.districtName?.contains(query, true) ?: false
        }
        else originalCitiesList

        submitList(null)
        submitList(result)

        return result.size
    }

    private val originalCitiesList = mutableListOf<DistrictModel>()
    fun setDistrictsList(data: List<DistrictModel>?) {
        originalCitiesList.clear()
        if (data != null) {
            originalCitiesList.addAll(data)
        }
        submitList(data)


    }

}