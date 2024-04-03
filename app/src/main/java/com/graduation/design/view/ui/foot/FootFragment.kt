package com.graduation.design.view.ui.foot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.graduation.design.databinding.FragmentFootBinding
import com.graduation.design.domian.adapter.AdapterFragment
import com.graduation.design.view.ui.download.DownloadFragment
import com.graduation.design.view.ui.history.HistoryFragment
import com.graduation.design.view.ui.rank.setUpWithTabLayout
import com.graduation.design.view.ui.subscribe.SubscribeFragment

class FootFragment : Fragment() {

    private var _binding: FragmentFootBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val footViewModel =
            ViewModelProvider(this).get(FootViewModel::class.java)

        _binding = FragmentFootBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.vm = footViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        initView(binding, footViewModel)

        return root
    }

    private fun initView(binding: FragmentFootBinding, footViewModel: FootViewModel) {
        //vp
        binding.vpFoot.adapter = AdapterFragment(
            requireActivity(),
            listOf(
                SubscribeFragment(footViewModel.liveVisibility),
                HistoryFragment(footViewModel.liveVisibility),
                DownloadFragment()
            )
        )
        //绑定
        binding.vpFoot.setUpWithTabLayout(binding.tabFoot, listOf("订阅", "历史", "缓存"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}