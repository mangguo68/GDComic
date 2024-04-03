package com.graduation.design.view.viewmodel.item.sort

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.graduation.design.model.pojo.SortData

class ItemSortVm(sortData: SortData) :ViewModel() {
    val liveSortData = MutableLiveData(sortData)
}