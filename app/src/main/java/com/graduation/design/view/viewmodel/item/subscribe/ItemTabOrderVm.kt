package com.graduation.design.view.viewmodel.item.subscribe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.graduation.design.model.pojo.SortItemParams

class ItemTabOrderVm(
    sortItemParams: SortItemParams,
) : ViewModel() {
    val liveSortItemParams = MutableLiveData(sortItemParams)
}