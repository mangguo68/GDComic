package com.graduation.design.view.ui.rank

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.graduation.design.R
import com.graduation.design.databinding.FragmentRankBinding
import com.graduation.design.domian.adapter.AdapterFragment
import com.graduation.design.domian.utils.MUtils
import com.graduation.design.model.pojo.RankType
import com.graduation.design.view.ui.rank.child.ChildRankFragment

fun ViewPager2.setUpWithTabLayout(tabLayout: TabLayout, list: List<String>) {
    TabLayoutMediator(
        tabLayout, this
    ) { tab, position ->
        if (position < list.size) tab.text = list[position]
    }.attach()
}

class RankFragment : Fragment() {

    companion object {
        fun newInstance() = RankFragment()
    }

    private lateinit var viewModel: RankViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentRankBinding>(
            inflater, R.layout.fragment_rank, container, false
        )

        initView(binding)

        return binding.root
    }

    private fun initView(binding: FragmentRankBinding?) {
        //vp
        binding!!.viewpagerRank.adapter = AdapterFragment(
            requireActivity(), listOf(
                ChildRankFragment(RankType.popularity_rank),
                ChildRankFragment(RankType.roast_rank),
                ChildRankFragment(RankType.subscribe_rank)
            )
        )

        //绑定vp
        MUtils.bindWithTabLayout(
            binding.viewpagerRank,
            binding.tabPopularSubscribeClick,
            listOf("人气排行", "吐槽排行", "订阅排行")
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RankViewModel::class.java)
        // TODO: Use the ViewModel
    }

}