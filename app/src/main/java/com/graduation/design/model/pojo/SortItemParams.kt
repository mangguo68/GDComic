package com.graduation.design.model.pojo

import android.view.View
import androidx.lifecycle.MutableLiveData

data class SortItemParams(
    var text: String = "订阅顺序",
    var state: MutableLiveData<Boolean> = MutableLiveData(false),
    var onTabSelectedListener: View.OnClickListener
)