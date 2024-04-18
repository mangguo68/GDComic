package com.graduation.design.view.viewmodel.item.sort

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.graduation.design.model.pojo.SortData
import com.kongzue.dialogx.dialogs.PopTip

class ItemSortVm(sortData: SortData) : ViewModel() {
    val liveSortData = MutableLiveData(sortData)

    fun onSortItemClicked(view: View) {
        //分类的漫画被点击的监听
        PopTip.show("跳转，target=" + liveSortData.value!!.target)
    }
}