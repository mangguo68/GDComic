package com.graduation.design.view.viewmodel.item.me

import android.view.View
import android.widget.RelativeLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.graduation.design.model.pojo.MyEntryData
import com.kongzue.dialogx.dialogs.PopTip

class ItemMyEntryVm(myEntryData: MyEntryData) : ViewModel() {
    val liveItemParams = MutableLiveData(myEntryData)

    fun onItemClicked(view: View){
        //入口被点击的监听
        when(liveItemParams.value!!.text){
            "帮助中心"->{
                PopTip.show("帮助中心")
            }
            "意见反馈"->{
                PopTip.show("意见反馈")

            }
            "我的消息"->{
                PopTip.show("我的消息")

            }
            else->{
                PopTip.show("敬请期待！！")

            }
        }

    }
}