package com.graduation.design.view.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.graduation.design.R
import com.graduation.design.databinding.FragmentHomeBinding
import com.graduation.design.databinding.ItemHomeRecommendBinding
import com.graduation.design.domian.adapter.AdapterBanner
import com.graduation.design.domian.adapter.AdapterRv
import com.graduation.design.domian.utils.MUtils
import com.graduation.design.model.pojo.BannerData
import com.graduation.design.model.pojo.HomeItemParameter
import com.graduation.design.model.pojo.SORF
import com.graduation.design.model.pojo.RecommendData
import com.graduation.design.model.result.Results
import com.graduation.design.view.viewmodel.item.home.ItemHomePage
import com.youth.banner.indicator.CircleIndicator

fun <T> ToastFailure(it: Results<List<T>>?, context: Context) {
    it?.let {
        if (it.code == SORF.R_FAILED) {
            MUtils.showTips(context, "网络错误！")
        }
    }
}

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(
            this,
        )[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //绑定vm
        binding.vm = homeViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        //初始化
        initView(binding, homeViewModel)
        initData(homeViewModel)

        return root
    }

    private fun initData(homeViewModel: HomeViewModel) {
        //异常处理
        homeViewModel.liveBannerData.observe(viewLifecycleOwner) {
            ToastFailure(it, requireContext())
        }
    }

    private fun initView(binding: FragmentHomeBinding, homeViewModel: HomeViewModel) {
        //banner
        binding.banner.addBannerLifecycleObserver(this)
            .setAdapter(object : AdapterBanner<BannerData>(listOf()) {
                override fun loadData(data: BannerData): String {
                    return data.imgUrl
                }
            }).setIndicator(CircleIndicator(context))
            .setIndicatorWidth(MUtils.dip2px(context, 8F), MUtils.dip2px(context, 9f))
            .setIndicatorNormalColorRes(R.color.indicator_normal)
            .setIndicatorSelectedColorRes(R.color.white)

        //rv
        binding.rvHome.adapter = object :
            AdapterRv<HomeItemParameter<Results<List<RecommendData>>>, ItemHomeRecommendBinding>(
                R.layout.item_home_recommend
            ) {
            override fun bind(
                context: Context,
                binding: ItemHomeRecommendBinding,
                data: HomeItemParameter<Results<List<RecommendData>>>
            ) {
                val itemHomePage = ItemHomePage(data)
                binding.vm = itemHomePage
                //异常处理
                itemHomePage.liveRecommendData.observe(viewLifecycleOwner) {
                    ToastFailure(it, requireContext())
                }
            }

        }
        binding.rvHome.layoutManager = object : LinearLayoutManager(context) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}