package com.graduation.design.view.ui.sort

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.graduation.design.R
import com.graduation.design.databinding.FragmentRankBinding
import com.graduation.design.databinding.FragmentSortBinding
import com.graduation.design.databinding.ItemSortBinding
import com.graduation.design.domian.adapter.AdapterRv
import com.graduation.design.model.pojo.SortData
import com.graduation.design.view.viewmodel.item.sort.ItemSortVm

class SortFragment : Fragment() {

    companion object {
        fun newInstance() = SortFragment()
    }

    private lateinit var viewModel: SortViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentSortBinding>(
            inflater,
            R.layout.fragment_sort,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(SortViewModel::class.java)

        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        initView(binding)
        return binding.root
    }

    private fun initView(binding: FragmentSortBinding?) {
        //呆会再说
        //rv
        binding!!.rvSort.adapter =
            object : AdapterRv<SortData, ItemSortBinding>(R.layout.item_sort) {
                override fun bind(context: Context, binding: ItemSortBinding, data: SortData) {
                    binding.vm = ItemSortVm(data)
                }
            }

        binding.rvSort.layoutManager = GridLayoutManager(context, 3)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}