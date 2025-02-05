package com.bosta.bostatask.presentation.ui.adapters.districts


import androidx.recyclerview.widget.DiffUtil
import com.bosta.bostatask.domain.models.DistrictModel

class DistrictsDataDC : DiffUtil.ItemCallback<DistrictModel>() {
    override fun areItemsTheSame(
        o: DistrictModel,
        n: DistrictModel
    ): Boolean =
        o.districtId == n.districtId

    override fun areContentsTheSame(
        o: DistrictModel,
        n: DistrictModel
    ): Boolean =
        o == n
}