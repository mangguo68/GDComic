package com.graduation.design.view.ui.subscribe

import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.graduation.design.R
import com.graduation.design.model.pojo.SortItemParams
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
    private val repository = MainRepository()
    val liveSubscribeRvData = liveData {
        emit(
            repository.getSubscribeData(1, SortType.subscribe_order)
        )
    }

    val onTabSelectedListener = OnClickListener { v ->
        //顺序按钮被点击的监听
        viewModelScope.launch {
            when ((v as TextView).text) {
                "订阅顺序" -> {
                    changeState(0)
                    (liveSubscribeRvData as MutableLiveData).value =
                        repository.getSubscribeData(1, SortType.subscribe_order)

                }

                "更新时间" -> {
                    changeState(1)
                    (liveSubscribeRvData as MutableLiveData).value =
                        repository.getSubscribeData(1, SortType.update_time)

                }

                else -> {
                    changeState(2)
                    (liveSubscribeRvData as MutableLiveData).value =
                        repository.getSubscribeData(1, SortType.recent_read)

                }
            }
        }
    }

    val liveTabRvData = MutableLiveData(
        listOf(
            SortItemParams("订阅顺序", MutableLiveData(true), onTabSelectedListener),
            SortItemParams("更新时间", onTabSelectedListener = onTabSelectedListener),
            SortItemParams("最近阅读", onTabSelectedListener = onTabSelectedListener),
        )
    )

    private fun changeState(index: Int) {
        liveTabRvData.value!!.forEach {
            it.state.value = false
        }
        liveTabRvData.value!![index].state.value = true
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
        //获取要删除的item的信息
        val shoppingCart = mutableListOf<SubscribeData>()
        liveSubscribeRvData.value!!.data.forEach {
            if (it.liveShoppingCart.value == true) {
                shoppingCart.add(it)
            }
        }
        var msg = ""
        shoppingCart.forEach {
            msg += it.name + "**"
        }
        PopTip.show("删除:$msg")
    }
}