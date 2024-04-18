package com.graduation.design.model.pojo

import android.icu.text.CaseMap.Title
import androidx.lifecycle.LiveData

data class HomeItemParameter<T>(
    var title: String = "我的订阅",
    var itemType: HomePageItemType = HomePageItemType.OtherType,
    var rvParameter: RvParameter<T>
)