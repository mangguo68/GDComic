package com.graduation.design.domian.binding;

import android.view.View;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;

import com.google.android.material.tabs.TabLayout;

public class TabBindingAdapter {
    @BindingAdapter("app:selectedTab")
    public static void setSelected(View view, Integer pos) {
        if (view instanceof TabLayout) {
            ((TabLayout) view).setScrollPosition(pos, 0, true);
        }
    }


    @InverseBindingAdapter(attribute = "app:selectedTab", event = "onSelected")
    public static Integer getSelectedPosition(View view) {
        if (view instanceof TabLayout) {
            return ((TabLayout) view).getSelectedTabPosition();
        } else {
            return 0;
        }
    }

    @BindingAdapter("onSelected")
    public static void setOnSelected(View view, InverseBindingListener changeListener) {
        if (changeListener != null) {
            if (view instanceof TabLayout) {
                ((TabLayout) view).addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        changeListener.onChange();
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });
            }
        }
    }

    @BindingAdapter("app:onSelectedListener")
    public static void setOnSelectedListener(View view, TabLayout.OnTabSelectedListener listener) {
        if (view instanceof TabLayout) {
            ((TabLayout) view).addOnTabSelectedListener(listener);
        }
    }
}
