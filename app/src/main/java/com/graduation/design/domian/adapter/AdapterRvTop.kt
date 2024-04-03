package com.graduation.design.domian.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner

abstract class AdapterRvTop<T, V : ViewDataBinding, K : ViewDataBinding>(
    itemId: Int, val itemIdTop: Int
) : AdapterRv<T, V>(itemId) {
    val type_top = 0x1011011

    override fun getItemViewType(position: Int): Int {
        if (position == 0) return type_top
        return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        context = parent.context
        var inflatedBinding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context), itemId, parent, false
        )
        if (viewType == type_top) {
            inflatedBinding = DataBindingUtil.inflate(
                LayoutInflater.from(context), itemIdTop, parent, false
            )
        }
        inflatedBinding.lifecycleOwner = context as LifecycleOwner
        return MViewHolder(inflatedBinding.root)
    }

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        val binding: ViewDataBinding? = DataBindingUtil.getBinding(holder.item)
        when (holder.itemViewType) {
            type_top -> {
                @Suppress("UNCHECKED_CAST") bindTop(
                    context!!,
                    binding as K,
                    mData[holder.adapterPosition]
                )
            }

            else -> {
                @Suppress("UNCHECKED_CAST") bind(
                    context!!,
                    binding as V,
                    mData[holder.adapterPosition]
                )
            }
        }
    }

    abstract override fun bind(context: Context, binding: V, data: T)

    abstract fun bindTop(context: Context, binding: K, data: T)
}