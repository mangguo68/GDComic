package com.graduation.design.model.result

import com.graduation.design.model.pojo.SORF

data class Results<T>(var code: SORF, var data: T) {
    companion object {
        fun <T> succeed(data: T) = Results(SORF.R_SUCCEED, data)
        fun <T> failed(data: T) = Results(SORF.R_FAILED, data)
    }
}