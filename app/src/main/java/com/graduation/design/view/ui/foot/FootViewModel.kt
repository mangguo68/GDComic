package com.graduation.design.view.ui.foot

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.Tab
import com.graduation.design.model.pojo.SubscribeData
import com.graduation.design.model.result.Results

class FootViewModel : ViewModel() {

    val liveVisibility = MutableLiveData(View.GONE)

    var selectedTab = Tab()

    val onTabSelectedListener = object : OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            if (tab != null) {
                selectedTab = tab
            }
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {}

        override fun onTabReselected(tab: TabLayout.Tab?) {}
    }

    fun onEditClick(view: View) {
        //编辑按钮的监听
        //判断当前选中的tab是哪一个
        //展示ui
        if (liveVisibility.value == View.GONE)
            liveVisibility.value = View.VISIBLE
        else
            liveVisibility.value = View.GONE

    }

}