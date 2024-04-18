package com.graduation.design.view.ui.subscribe

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.graduation.design.R
import com.graduation.design.databinding.FragmentSubscribeBinding
import com.graduation.design.databinding.ItemSortTabBinding
import com.graduation.design.databinding.ItemSubscribeBinding
import com.graduation.design.domian.adapter.AdapterRv
import com.graduation.design.domian.adapter.AdapterRvEmpty
import com.graduation.design.model.pojo.SortItemParams
import com.graduation.design.model.pojo.SubscribeData
import com.graduation.design.view.viewmodel.item.subscribe.ItemSubscribeVm
import com.graduation.design.view.viewmodel.item.subscribe.ItemTabOrderVm

class SubscribeFragment(
    val liveVisibility: MutableLiveData<Int>,
) : Fragment() {

    private lateinit var viewModel: SubscribeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = SubscribeViewModel(liveVisibility)

        val binding: FragmentSubscribeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_subscribe, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = viewModel

        initViews(binding,viewModel)
        initData(binding, viewModel)

        return binding.root
    }

    private fun initData(binding: FragmentSubscribeBinding, viewModel: SubscribeViewModel) {

    }

    private fun initViews(binding: FragmentSubscribeBinding, viewModel: SubscribeViewModel) {

        //rv
        binding.rvFootSubscribe.adapter = object :
            AdapterRvEmpty<SubscribeData, ItemSubscribeBinding>(
                R.layout.item_subscribe,
                R.layout.item_subscribe_empty
            ) {
            override fun bind(
                context: Context,
                binding: ItemSubscribeBinding,
                data: SubscribeData
            ) {
                binding.vm = ItemSubscribeVm(data, liveVisibility)
            }

            override fun onEmpty() {
                binding.rvFootSubscribe.layoutManager = LinearLayoutManager(context)
            }

            override fun onNonEmpty() {
                binding.rvFootSubscribe.layoutManager = GridLayoutManager(context, 3)
            }

        }

        //tab rv
        binding.rvTopTab.adapter= object : AdapterRv<SortItemParams,ItemSortTabBinding>(R.layout.item_sort_tab) {
            override fun bind(context: Context, binding: ItemSortTabBinding, data: SortItemParams) {
                binding.vm = ItemTabOrderVm(data)
            }
        }

        binding.rvTopTab.layoutManager=LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
    }

}