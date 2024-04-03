package com.graduation.design.model.pojo

import androidx.lifecycle.MutableLiveData

data class HistoryData(
    var cover: String = "https://www.bilibili.com",
    var name: String = "看得见哦",
    var history: String = "看到xx话",
    var date: String = "xxxx-xx-xx",
    var target: String = "https://www.bilibili.com",
    val liveShoppingCart: MutableLiveData<Boolean> = MutableLiveData(false)
)
