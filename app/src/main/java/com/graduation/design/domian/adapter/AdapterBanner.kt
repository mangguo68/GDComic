package com.graduation.design.domian.adapter

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.graduation.design.R
import com.graduation.design.domian.adapter.AdapterBanner.BannerViewHolder
import com.youth.banner.adapter.BannerAdapter

/**
 * BannerAdapter v1.0
 */
abstract class AdapterBanner<T>(mData: List<T>?) : BannerAdapter<T, BannerViewHolder>(mData) {
    //创建ViewHolder，可以用viewType这个字段来区分不同的ViewHolder
    override fun onCreateHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val imageView = ImageView(parent.context)
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        imageView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        )
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        return BannerViewHolder(imageView)
    }

    override fun onBindView(holder: BannerViewHolder, data: T, position: Int, size: Int) {
        //加载数据
        Glide.with(holder.itemView).load(loadData(data)).placeholder(R.drawable.ic_404)
            .error(R.drawable.ic_404).into(holder.imageView)

    }

    abstract fun loadData(data: T): String?
    class BannerViewHolder(var imageView: ImageView) : RecyclerView.ViewHolder(
        imageView
    )
}
