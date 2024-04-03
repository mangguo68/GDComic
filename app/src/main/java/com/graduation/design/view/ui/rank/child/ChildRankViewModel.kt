package com.graduation.design.view.ui.rank.child

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.graduation.design.model.pojo.RankType
import com.graduation.design.repository.MainRepository
import com.kongzue.dialogx.dialogs.PopTip
import com.scwang.smart.refresh.layout.api.RefreshLayout
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ChildRankViewModel(rankType: RankType) : ViewModel() {
    // TODO: Implement the ViewModel
    val repository = MainRepository()
    val liveRankRvData = liveData {
        emit(repository.getRankData(rankType))
    }

    fun onRefresh(refreshLayout: RefreshLayout) {
        //下拉刷新的监听
        PopTip.show("刷新")
        viewModelScope.launch {
            delay(2000)
            refreshLayout.finishRefresh()
        }
    }
}