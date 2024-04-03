package com.graduation.design.domian.binding;

import android.media.Session2Command;
import android.view.View;

import androidx.databinding.BindingAdapter;

import com.graduation.design.model.pojo.SORF;
import com.graduation.design.model.result.Results;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.listener.OnBannerListener;

import java.util.List;

public class BannerBindingAdapter {
    @BindingAdapter("app:bannerData")
    public static <T> void setBannerData(View view, Results<List<T>> data) {
        if (view instanceof Banner) {
            BannerAdapter adapter = ((Banner) view).getAdapter();
            if (adapter != null && data != null) {
                if (data.getCode() == SORF.R_SUCCEED) {
                    //请求成功
                    adapter.setDatas(data.getData());
                }
            }
        }
    }

    @BindingAdapter("app:onBannerItemClicked")
    public static <T> void setOnBannerItemClicked(View view, OnBannerListener<T> onBannerListener) {
        if (view instanceof Banner) {
            ((Banner) view).setOnBannerListener(onBannerListener);
        }
    }
}
