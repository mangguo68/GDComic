package com.graduation.design.view.ui.sort

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.google.android.material.tabs.TabLayout
import com.graduation.design.domian.utils.DiffLiveData
import com.graduation.design.repository.MainRepository
import com.kongzue.dialogx.dialogs.PopTip
import com.scwang.smart.refresh.layout.api.RefreshLayout
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SortViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    val repository = MainRepository()
    val liveTabSelectionList = listOf(
        DiffLiveData(0),
        DiffLiveData(0),
        DiffLiveData(0),
        DiffLiveData(0),
    )

    val liveSortRvData = liveData {
        emit(repository.getSortData(mutableListOf<Int>().also {
            liveTabSelectionList.forEach { item ->
                it.add(item.value!!)
            }
        }))
    }

    val onTabSelectedListener = object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab) {
            //在这里面去做发送请求的操作
            //暂时不做
            PopTip.show("发送请求，请求参数：" + liveTabSelectionList[0].value + "-" + liveTabSelectionList[1].value + "-" + liveTabSelectionList[2].value + "-" + liveTabSelectionList[3].value)
        }

        override fun onTabUnselected(tab: TabLayout.Tab) {}
        override fun onTabReselected(tab: TabLayout.Tab) {}
    }

    //下拉刷新
    fun onRefresh(refreshLayout: RefreshLayout){
        PopTip.show("刷新")
        viewModelScope.launch {
            delay(2000)
            refreshLayout.finishRefresh()
        }
    }
    //上拉加载
    fun onLoadMore(refreshLayout: RefreshLayout){
        PopTip.show("加载...")
        viewModelScope.launch {
            delay(2000)
            refreshLayout.finishLoadMore()
        }
    }
}