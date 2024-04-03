package com.graduation.design.model.pojo

import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.graduation.design.domian.adapter.AdapterRv
import com.graduation.design.domian.binding.BannerBindingAdapter

data class RvParameter<T>(
    var data: LiveData<T>,
    var adapter: AdapterRv<*, *>,
    var layoutManager: LayoutManager
) {
}