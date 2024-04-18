package com.graduation.design.view.ui.me

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.graduation.design.R
import com.graduation.design.databinding.FragmentMeBinding
import com.graduation.design.databinding.ItemMyEntryBinding
import com.graduation.design.domian.adapter.AdapterRv
import com.graduation.design.model.pojo.MyEntryData
import com.graduation.design.view.viewmodel.item.me.ItemMyEntryVm

class MeFragment : Fragment() {

    companion object {
        fun newInstance() = MeFragment()
    }

    private lateinit var viewModel: MeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(MeViewModel::class.java)
        val binding = DataBindingUtil.inflate<FragmentMeBinding>(
            inflater,
            R.layout.fragment_me,
            container,
            false
        )

        binding.vm = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        initViews(binding)
        return binding.root
    }

    private fun initViews(binding: FragmentMeBinding?) {

        //rv
        binding!!.rvMyEntry.adapter =
            object : AdapterRv<MyEntryData, ItemMyEntryBinding>(R.layout.item_my_entry) {
                override fun bind(
                    context: Context,
                    binding: ItemMyEntryBinding,
                    data: MyEntryData
                ) {
                    binding.vm = ItemMyEntryVm(data)
                }

            }

        binding.rvMyEntry.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)


    }

}