package com.graduation.design.view.viewmodel.item.rank

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.graduation.design.model.pojo.RankData
import com.kongzue.dialogx.dialogs.PopTip

class RankOtherVm(rankData: RankData) : ViewModel() {
    val liveRankData = MutableLiveData(rankData)
    fun onItemClicked(view: View){
        //item被点击的监听
        PopTip.show("跳转，target="+ liveRankData.value!!.rankDataOther.target)
    }
}