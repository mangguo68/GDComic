package com.graduation.design.domian.utils

import androidx.lifecycle.MutableLiveData
import java.util.Objects

class DiffLiveData<T>(value: T) : MutableLiveData<T>(value) {

    override fun setValue(value: T?) {
        if (Objects.equals(value, getValue())) return
        super.setValue(value)
    }

    override fun postValue(value: T?) {
        if (Objects.equals(value, getValue())) return
        super.postValue(value)
    }
}