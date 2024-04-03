package com.graduation.design.view.viewmodel.item.subscribe

import android.opengl.Visibility
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.graduation.design.model.pojo.SubscribeData
import com.kongzue.dialogx.dialogs.PopTip

class ItemSubscribeVm(subscribeData: SubscribeData, liveVisibility: MutableLiveData<Int>) :
    ViewModel() {
    val liveSubscribeData = MutableLiveData(subscribeData)

    val liveCheckBoxVisibility = liveVisibility

    val liveCheckBoxState = subscribeData.liveShoppingCart

    fun onItemClicked(view: View) {
        //item被点击的时候的监听
        PopTip.show("跳转，target=" + liveSubscribeData.value?.target)
    }

    fun onCheckBoxClicked(view: View) {
        //checkbox被点击的时候的监听
        liveCheckBoxState.value = !liveCheckBoxState.value!!
    }
}