package com.graduation.design.view.ui.found

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.graduation.design.databinding.FragmentFoundBinding
import com.graduation.design.domian.adapter.AdapterFragment
import com.graduation.design.view.ui.rank.RankFragment
import com.graduation.design.view.ui.sort.SortFragment

class FoundFragment : Fragment() {

    private var _binding: FragmentFoundBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val foundViewModel =
            ViewModelProvider(this).get(FoundViewModel::class.java)

        _binding = FragmentFoundBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initView(binding)
        return root
    }

    private fun initView(binding: FragmentFoundBinding) {
        //vp
        binding.vpRankSort.adapter =
            AdapterFragment(requireActivity(), listOf(RankFragment(), SortFragment()))

        //绑定vp
        TabLayoutMediator(binding.tabRankSort,binding.vpRankSort
        ) { tab, position ->
            when (position) {
                0 -> tab.setText("排行")
                1 -> tab.setText("分类")
            }
        }.attach()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}