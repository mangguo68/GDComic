package com.graduation.design.view.viewmodel.item.rank

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.graduation.design.model.pojo.RankData
import com.kongzue.dialogx.dialogs.PopTip

class RankFrontVm(rankData: RankData) : ViewModel() {
    val liveRankData = MutableLiveData(rankData)

    fun onCenterItemClicked(view: View) {
        //中间的监听
        PopTip.show("中间，跳转，target=" + liveRankData.value!!.rankDataFront[0].target)
    }

    fun onLeftItemClicked(view: View) {
        //左边的监听
        PopTip.show("左边，跳转，target=" + liveRankData.value!!.rankDataFront[1].target)

    }

    fun onRightItemClicked(view: View) {
        //右边的监听
        PopTip.show("右边，跳转，target=" + liveRankData.value!!.rankDataFront[2].target)

    }
}