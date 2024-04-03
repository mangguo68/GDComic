package com.graduation.design.domian.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner

/**
 * 版本 v1.1
 * 更新：移除了context，新增lifecycle owner
 * 实现的功能是双布局的情况下适用
 */
@Suppress("UNCHECKED_CAST")
abstract class AdapterRvEmpty<T, V : ViewDataBinding>(
    itemId: Int, private var itemIdEmpty: Int
) : AdapterRv<T, V>(itemId) {
    val empty_view_type = 0x101110

    override var mData: List<T> = listOf()
        set(value) {
            field = value
            if (value.isEmpty())
            //空布局
                onEmpty()
            else onNonEmpty()
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        context = parent.context
        var inflatedBinding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context), itemId, parent, false
        )
        if (viewType == empty_view_type) {
            inflatedBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context), itemIdEmpty, parent, false
            )
        }
        inflatedBinding.lifecycleOwner = context as LifecycleOwner
        return MViewHolder(inflatedBinding.root)
    }

    override fun getItemViewType(position: Int): Int {
        if (mData.isEmpty()) return empty_view_type
        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        val binding: ViewDataBinding? = DataBindingUtil.getBinding(holder.item)
        if (holder.itemViewType != empty_view_type) bind(
            context!!,
            binding as V,
            mData[holder.adapterPosition]
        )
    }

    override fun getItemCount(): Int {
        if (mData.isEmpty()) return 1
        return mData.size
    }

    abstract fun onEmpty()

    abstract fun onNonEmpty()
}