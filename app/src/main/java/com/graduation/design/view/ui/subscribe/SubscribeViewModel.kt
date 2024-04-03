package com.graduation.design.view.ui.subscribe

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.graduation.design.R
import com.graduation.design.model.pojo.SortType
import com.graduation.design.model.pojo.SubscribeData
import com.graduation.design.model.result.Results
import com.graduation.design.repository.MainRepository
import com.kongzue.dialogx.dialogs.PopTip
import com.scwang.smart.refresh.layout.api.RefreshLayout
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SubscribeViewModel(
    liveVisibility: MutableLiveData<Int>,
) : ViewModel() {
    // TODO: Implement the ViewModel
    val repository = MainRepository()
    val liveSubscribeRvData = liveData {
        emit(
            repository.getSubscribeData(1, SortType.subscribe_order)
        )
    }

    val onTabSelectedListener = object : OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            //tab选中的监听（排序的监听）

            viewModelScope.launch {
                when (tab!!.text) {
                    "订阅顺序" -> {
                        (liveSubscribeRvData as MutableLiveData).value =
                            repository.getSubscribeData(1, SortType.subscribe_order)
                    }

                    "更新时间" -> {
                        (liveSubscribeRvData as MutableLiveData).value =
                            repository.getSubscribeData(1, SortType.update_time)
                    }

                    else -> {
                        (liveSubscribeRvData as MutableLiveData).value =
                            repository.getSubscribeData(1, SortType.recent_read)
                    }
                }
            }

        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {}

        override fun onTabReselected(tab: TabLayout.Tab?) {}
    }

    val liveToolBarVisibility = liveVisibility

    fun onRefresh(refreshLayout: RefreshLayout) {
        //下拉刷新的监听
        PopTip.show("刷新")
        viewModelScope.launch {
            delay(2000)
            refreshLayout.finishRefresh()
        }
    }

    fun onLoadMore(refreshLayout: RefreshLayout) {
        //加载更多的监听
        PopTip.show("加载...")
        viewModelScope.launch {
            delay(2000)
            refreshLayout.finishLoadMore()
        }
    }

    fun onSelectAll(view: View) {
        //全选的监听
        liveSubscribeRvData.value!!.data.forEach {
            it.liveShoppingCart.value = true
        }
    }

    fun onUnSelectAll(view: View) {
        //反选的监听
        liveSubscribeRvData.value!!.data.forEach {
            it.liveShoppingCart.value = !it.liveShoppingCart.value!!
        }
    }

    fun onDelete(view: View) {
        //删除的监听
        PopTip.show("删除")
    }
}