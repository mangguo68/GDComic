package com.graduation.design.view.ui.history

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.graduation.design.model.pojo.HistoryData
import com.graduation.design.model.result.Results
import com.kongzue.dialogx.dialogs.PopTip
import com.scwang.smart.refresh.layout.api.RefreshLayout
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HistoryViewModel(
    liveVisibility: MutableLiveData<Int>,
) : ViewModel() {
    // TODO: Implement the ViewModel
    val liveToolBarVisibility = liveVisibility
    val liveHistoryRvData = liveData {
        emit(
            Results.succeed(
                listOf(
                    HistoryData(),
                    HistoryData(),
                    HistoryData(),
                    HistoryData(),
                    HistoryData(),
                    HistoryData(),
                )
            )
        )
    }

    fun onRefresh(refreshLayout: RefreshLayout) {
        //下拉刷新
        PopTip.show("刷新")
        viewModelScope.launch {
            delay(2000)
            refreshLayout.finishRefresh()
        }
    }

    fun onLoadMore(refreshLayout: RefreshLayout) {
        //上拉加载
        PopTip.show("加载...")
        viewModelScope.launch {
            delay(2000)
            refreshLayout.finishLoadMore()
        }
    }

    fun onSelectAll(view: View) {
        //全选的监听
        liveHistoryRvData.value!!.data.forEach {
            it.liveShoppingCart.value = true
        }
    }

    fun onDelete(view: View) {
        //删除的监听
        //获取要删除的东西
        val deleteItems = mutableListOf<HistoryData>()
        liveHistoryRvData.value!!.data.forEach {
            if (it.liveShoppingCart.value == true) {
                deleteItems.add(it)
            }
        }
        var msg = ""

        deleteItems.forEach {
            msg += it.name + "*"
        }
        PopTip.show("删除:$msg")
    }
}