package com.graduation.design.domian.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView

/**
 * 版本 v1.1
 * 更新：现在已经不需要context了，解决了嵌套recycleView不生效的问题（其实就是增加了一个lifecycleOwner）
 * 实现的功能是单布局的情况下适用
 */
abstract class AdapterRv<T, V : ViewDataBinding>(
    var itemId: Int
) : RecyclerView.Adapter<AdapterRv.MViewHolder>() {
    var context: Context? = null
    open var mData: List<T> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class MViewHolder(val item: View) : RecyclerView.ViewHolder(item)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MViewHolder {
        context = parent.context
        val inflate = DataBindingUtil.inflate<V>(
            LayoutInflater.from(context),
            itemId,
            parent,
            false
        )
        inflate.lifecycleOwner = parent.context as LifecycleOwner
        return MViewHolder(inflate.root)
    }

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        val binding = DataBindingUtil.getBinding<V>(holder.item)
        bind(context!!, binding!!, mData[holder.adapterPosition])
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    abstract fun bind(context: Context, binding: V, data: T)
}