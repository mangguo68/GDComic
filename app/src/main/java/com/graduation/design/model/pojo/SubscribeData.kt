package com.graduation.design.model.pojo

import android.opengl.Visibility
import android.view.View
import androidx.lifecycle.MutableLiveData

data class SubscribeData(
    var cover: String = "https://www.bilibili.com",
    var name: String = "看得见哦",
    var history: String = "看到xx话",
    var target: String = "https://www.bilibili.com",
    var liveShoppingCart: MutableLiveData<Boolean> = MutableLiveData(false)
)
