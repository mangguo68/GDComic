package com.graduation.design.model.pojo

import java.text.FieldPosition

data class RankData(
    var rankDataFront: List<RankDataOther> = emptyList(),
    var rankDataOther: RankDataOther = RankDataOther(),
    var rankPosition: Int = 1
)
