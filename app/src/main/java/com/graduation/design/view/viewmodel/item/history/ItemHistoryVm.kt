package com.graduation.design.view.viewmodel.item.history

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.graduation.design.model.pojo.HistoryData
import com.kongzue.dialogx.dialogs.PopTip

class ItemHistoryVm(historyData: HistoryData, liveVisibility: MutableLiveData<Int>) : ViewModel() {
    val liveHistoryData = MutableLiveData(historyData)
    val liveCheckBoxVisibility = liveVisibility
    val liveCheckBoxState = historyData.liveShoppingCart

    fun onCheckBoxClicked(view: View) {
        //checkbox的监听
        liveCheckBoxState.value = !liveCheckBoxState.value!!
    }

    fun onItemCoverClicked(view: View) {
        //点击封面的监听
        PopTip.show("历史->漫画详情")
    }

    fun onContinueClicked(view: View) {
        //须看的监听
        PopTip.show("续看")
    }
}