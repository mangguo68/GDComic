package com.graduation.design.view.ui.rank.child

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.graduation.design.R
import com.graduation.design.databinding.FragmentChildRankBinding
import com.graduation.design.databinding.ItemRankFrontBinding
import com.graduation.design.databinding.ItemRankOtherBinding
import com.graduation.design.domian.adapter.AdapterRvTop
import com.graduation.design.model.pojo.RankData
import com.graduation.design.model.pojo.RankType
import com.graduation.design.view.ui.home.ToastFailure
import com.graduation.design.view.viewmodel.item.rank.RankFrontVm
import com.graduation.design.view.viewmodel.item.rank.RankOtherVm

class ChildRankFragment(val rankType: RankType) : Fragment() {

    private lateinit var viewModel: ChildRankViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentChildRankBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_child_rank, container, false)
        viewModel = ChildRankViewModel(rankType)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = viewModel

        initView(binding)
        initData(viewModel)
        return binding.root
    }

    private fun initData(viewModel: ChildRankViewModel) {
        //异常处理
        viewModel.liveRankRvData.observe(viewLifecycleOwner) {
            ToastFailure(it, requireContext())
        }

    }

    private fun initView(binding: FragmentChildRankBinding) {
        //rv
        binding.rvRank.adapter = object :
            AdapterRvTop<RankData, ItemRankOtherBinding, ItemRankFrontBinding>(
                R.layout.item_rank_other,
                R.layout.item_rank_front
            ) {
            override fun bind(context: Context, binding: ItemRankOtherBinding, data: RankData) {
                binding.vm = RankOtherVm(data)
            }

            override fun bindTop(context: Context, binding: ItemRankFrontBinding, data: RankData) {
                binding.vm = RankFrontVm(data)
            }

        }

        binding.rvRank.layoutManager = LinearLayoutManager(context)
    }

}