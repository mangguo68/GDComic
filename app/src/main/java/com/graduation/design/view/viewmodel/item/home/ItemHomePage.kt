package com.graduation.design.view.viewmodel.item.home

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.graduation.design.model.pojo.HomeItemParameter
import com.graduation.design.model.pojo.HomePageItemType
import com.graduation.design.model.pojo.RecommendData
import com.graduation.design.model.result.Results
import com.kongzue.dialogx.dialogs.PopTip

class ItemHomePage(homeItemParameter: HomeItemParameter<Results<List<RecommendData>>>) : ViewModel() {
    val liveItemParameter = MutableLiveData(homeItemParameter)
    val liveVisibilityA =
        MutableLiveData(if (homeItemParameter.itemType == HomePageItemType.OtherType) View.VISIBLE else View.INVISIBLE)
    val liveVisibilityB =
        MutableLiveData(if (homeItemParameter.itemType == HomePageItemType.SubscribeType) View.VISIBLE else View.INVISIBLE)

    //加载主页里面的其他数据
    val liveRecommendData: LiveData<Results<List<RecommendData>>> = homeItemParameter.rvParameter.data

    fun onAllClick(view: View) {
        //全部的监听
        PopTip.show("全部")

    }

    fun onViewSubscribeClick(view: View) {
        //查看订阅的监听
        PopTip.show("查看订阅")
    }
}