package com.graduation.design.view.ui.history

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.graduation.design.R
import com.graduation.design.databinding.FragmentHistoryBinding
import com.graduation.design.databinding.ItemHistoryBinding
import com.graduation.design.domian.adapter.AdapterRvEmpty
import com.graduation.design.model.pojo.HistoryData
import com.graduation.design.view.viewmodel.item.history.ItemHistoryVm

class HistoryFragment(
    val liveVisibility:MutableLiveData<Int>
) : Fragment() {

    private lateinit var viewModel: HistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = HistoryViewModel(liveVisibility)

        val binding: FragmentHistoryBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)

        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        initViews(binding)
        return binding.root
    }

    private fun initViews(binding: FragmentHistoryBinding) {
        //rv
        binding.rvHistory.adapter = object : AdapterRvEmpty<HistoryData, ItemHistoryBinding>(
            R.layout.item_history,
            R.layout.item_subscribe_empty
        ) {
            override fun bind(context: Context, binding: ItemHistoryBinding, data: HistoryData) {
                binding.vm = ItemHistoryVm(data,liveVisibility)
            }

            override fun onEmpty() {}

            override fun onNonEmpty() {
                binding.rvHistory.layoutManager = LinearLayoutManager(context)
            }

        }
    }

}