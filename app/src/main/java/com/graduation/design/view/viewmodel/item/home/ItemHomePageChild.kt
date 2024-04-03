package com.graduation.design.view.viewmodel.item.home

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.graduation.design.model.pojo.RecommendData
import com.kongzue.dialogx.dialogs.PopTip

class ItemHomePageChild(data: RecommendData) : ViewModel() {
    val liveSubscribeData = MutableLiveData(data)

    fun onComicClick(view: View) {
        //漫画被点击的监听
        PopTip.show("跳转，target=" + liveSubscribeData.value!!.target)

    }
}