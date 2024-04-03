package com.graduation.design.view.ui.home

import android.app.Application
import android.content.Context
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.graduation.design.R
import com.graduation.design.databinding.ItemRecommendBinding
import com.graduation.design.domian.adapter.AdapterRv
import com.graduation.design.model.pojo.BannerData
import com.graduation.design.model.pojo.HomeItemParameter
import com.graduation.design.model.pojo.HomePageItemType
import com.graduation.design.model.pojo.RecommendData
import com.graduation.design.model.pojo.RvParameter
import com.graduation.design.model.result.Results
import com.graduation.design.repository.MainRepository
import com.graduation.design.view.viewmodel.item.home.ItemHomePageChild
import com.kongzue.dialogx.dialogs.PopTip
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.youth.banner.listener.OnBannerListener
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class HomeViewModel(context: Application) : AndroidViewModel(context) {
    val repository = MainRepository()

    //banner的数据
    val liveBannerData = liveData {
        emit(repository.getBannerData())
    }

    //rv的数据
    var rvValue = listOf<HomeItemParameter<Results<List<RecommendData>>>>(
        HomeItemParameter(itemType = HomePageItemType.SubscribeType,
            rvParameter = RvParameter(liveData {
                emit(repository.getHomePageData(0))
            }, object : AdapterRv<RecommendData, ItemRecommendBinding>(
                R.layout.item_recommend
            ) {
                override fun bind(
                    context: Context, binding: ItemRecommendBinding, data: RecommendData
                ) {
                    binding.vm = ItemHomePageChild(data)
                }

            }, LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            )
        ),
        HomeItemParameter(
            "最近更新", rvParameter = RvParameter(liveData {
                emit(repository.getHomePageData(1))
            }, object : AdapterRv<RecommendData, ItemRecommendBinding>(
                R.layout.item_recommend
            ) {
                override fun bind(
                    context: Context, binding: ItemRecommendBinding, data: RecommendData
                ) {
                    binding.vm = ItemHomePageChild(data)
                }

            }, object : GridLayoutManager(context, 3) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            })
        ),
        HomeItemParameter(
            "精品力荐", rvParameter = RvParameter(liveData {
                emit(repository.getHomePageData(2))
            }, object : AdapterRv<RecommendData, ItemRecommendBinding>(
                R.layout.item_recommend
            ) {
                override fun bind(
                    context: Context, binding: ItemRecommendBinding, data: RecommendData
                ) {
                    binding.vm = ItemHomePageChild(data)
                }

            }, object : GridLayoutManager(context, 3) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            })
        ),
    )

    //主页推荐的数据
    val liveRvData = MutableLiveData(rvValue)

    val bannerListener =
        OnBannerListener<BannerData> { data, position ->
            //banner的点击的监听
            PopTip.show("跳转，target=" + data.target)
        }

    fun onSearchClick(view: View) {
        //搜索按钮的监听
        PopTip.show("搜索")
    }

    fun onHistoryClick(view: View) {
        //历史的快捷入口的监听
        PopTip.show("历史")

    }

    fun onRankClick(view: View) {
        //排行的快捷入口的监听
        PopTip.show("排行")

    }

    fun onSortClick(view: View) {
        //分类的快捷入口的监听
        PopTip.show("分类")

    }

    fun onRefresh(refreshLayout: RefreshLayout) {
        //下拉刷新的监听
        viewModelScope.launch {
            //要做刷新的操作
            val deferreds = mutableListOf(
                async {
                    (liveBannerData as MutableLiveData).value = repository.getBannerData()
                },
            )
            repeat(rvValue.size) {
                deferreds.add(async {
                    (rvValue[it].rvParameter.data as MutableLiveData).value =
                        repository.getHomePageData(it)
                })
            }
            deferreds.awaitAll()
            refreshLayout.finishRefresh()
        }
    }
}