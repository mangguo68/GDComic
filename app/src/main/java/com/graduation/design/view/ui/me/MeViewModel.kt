package com.graduation.design.view.ui.me

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.graduation.design.R
import com.graduation.design.model.pojo.MyData
import com.graduation.design.model.pojo.MyEntryData
import com.graduation.design.model.result.Results
import com.kongzue.dialogx.dialogs.PopTip

class MeViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    val liveRvData = MutableLiveData(
        listOf(
            MyEntryData(R.drawable.ic_help),
            MyEntryData(R.drawable.ic_advice, "意见反馈"),
            MyEntryData(R.drawable.ic_my_message, "我的消息"),
            MyEntryData(R.drawable.ic_more, "更多功能"),
        )
    )

    val liveMyData = liveData {
        emit(
            Results.succeed(
                MyData(),
            )
        )
    }

    fun onAvatarClicked(view: View) {
        //头像点击的监听
        PopTip.show("头像被点击了")
    }

    fun onSettingClicked(view: View) {
        //设置点击的监听
        PopTip.show("进入设置页面")
    }


}