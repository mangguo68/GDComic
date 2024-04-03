package com.graduation.design.domian.binding;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.graduation.design.domian.adapter.AdapterRv;
import com.graduation.design.model.result.Results;
import com.kongzue.dialogx.dialogs.PopTip;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.List;

/**
 * 版本 v1.0
 * 自定义的BindingAdapter，需要以下依赖：
 * //Glide
 * implementation "com.github.bumptech.glide:glide:4.15.1"
 */
public class CustomBindingAdapter {
    /**
     * 这是对recyclerView的绑定
     *
     * @param view  rv
     * @param mData dataList
     * @param <T>   无
     */
    @BindingAdapter("app:mData")
    public static <T> void setRvData(View view, List<T> mData) {
        if (view instanceof RecyclerView) {
            RecyclerView.Adapter adapter = ((RecyclerView) view).getAdapter();
            if (adapter != null) {
                ((AdapterRv<T, ? extends ViewDataBinding>) adapter).setMData(mData);
            }
        }
    }

    /**
     * ImageView的图片资源绑定，仅限网络图片，本地图片请直接用src
     *
     * @param view     imageView本省
     * @param imageUrl 图片资源的地址
     */
    @BindingAdapter(value = {"app:networkImage", "app:defaultImageResource", "app:errorImageResource"}, requireAll = false)
    public static void setImage(View view, String imageUrl, Drawable placeHolder, Drawable error) {
        if (view instanceof ImageView) {
            if (!TextUtils.isEmpty(imageUrl)) {
                Glide.with(view).load(imageUrl).placeholder(placeHolder).error(error).into((ImageView) view);
            }
        }
    }

    @BindingAdapter("app:adapter")
    public static <T> void setRvAdapter(View view, RecyclerView.Adapter mAdapter) {
        if (view instanceof RecyclerView) {
            ((RecyclerView) view).setAdapter(mAdapter);
        }
    }

    @BindingAdapter("app:layoutManager")
    public static <T> void setRvLayoutManager(View view, RecyclerView.LayoutManager layoutManager) {
        if (view instanceof RecyclerView) {
            ((RecyclerView) view).setLayoutManager(layoutManager);
        }
    }

    @BindingAdapter("app:resultData")
    public static <T> void setRvResultData(View view, Results<List<T>> mData) {
        if (view instanceof RecyclerView) {
            RecyclerView.Adapter adapter = ((RecyclerView) view).getAdapter();
            if (adapter != null && mData != null) {
                ((AdapterRv<T, ? extends ViewDataBinding>) adapter).setMData(mData.getData());
            }
        }
    }

    @BindingAdapter("app:onRefresh")
    public static void setRefresh(View view, OnRefreshListener onRefresh) {
        if (view instanceof SmartRefreshLayout) {
            ((SmartRefreshLayout) view).setOnRefreshListener(onRefresh);
        }
    }

    @BindingAdapter("app:onLoadMore")
    public static void setOnLoadMore(View view, OnLoadMoreListener onLoad) {
        if (view instanceof SmartRefreshLayout) {
            ((SmartRefreshLayout) view).setOnLoadMoreListener(onLoad);
        }
    }

    @BindingAdapter("app:isSelected")
    public static void setSelected(View view, Boolean selected) {
        if (selected != null)
            view.setSelected(selected);
    }
}
