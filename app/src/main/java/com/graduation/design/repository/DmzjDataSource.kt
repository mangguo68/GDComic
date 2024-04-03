package com.graduation.design.repository

import androidx.lifecycle.MutableLiveData
import com.graduation.design.R
import com.graduation.design.model.pojo.BannerData
import com.graduation.design.model.pojo.RankData
import com.graduation.design.model.pojo.RankDataOther
import com.graduation.design.model.pojo.RankType
import com.graduation.design.model.pojo.RecommendData
import com.graduation.design.model.pojo.SortData
import com.graduation.design.model.pojo.SortType
import com.graduation.design.model.pojo.SubscribeData
import kotlinx.coroutines.delay

class DmzjDataSource {
    suspend fun getBannerData(): List<BannerData> {
        //模拟请求
        delay(2000)
        return listOf(
            BannerData("https://images.idmzj.com/tuijian/750_480/20231124-750x480.jpg"),
            BannerData("https://images.idmzj.com/tuijian/750_480/240325maoligouqitj1.jpg"),
            BannerData("https://images.idmzj.com/tuijian/750_480/240328dijiuyinengzhongxuetj1.jpg"),
            BannerData("https://images.idmzj.com/tuijian/750_480/231130xinwentj1.jpg"),
            BannerData("https://images.idmzj.com/tuijian/750_480/230918xinman114tj2.jpg"),
        )
    }

    suspend fun getSubscribeData(): List<RecommendData> {
        delay(2000)
        return listOf(
            RecommendData(),
            RecommendData(),
            RecommendData(),
            RecommendData(),
            RecommendData(),
            RecommendData(),
        )
    }

    suspend fun getRecentUpdateData(): List<RecommendData> {
        delay(2000)
        return listOf(
            RecommendData(),
            RecommendData(),
            RecommendData(),
            RecommendData(),
            RecommendData(),
            RecommendData(),
        )
    }

    suspend fun getFineRecommendData(): List<RecommendData> {
        delay(2000)
        return listOf(
            RecommendData(),
            RecommendData(),
            RecommendData(),
            RecommendData(),
            RecommendData(),
            RecommendData(),
        )
    }

    suspend fun getRankSubscribeData(): List<RankData> {
        delay(2000)
        return listOf(
            RankData(
                listOf(
                    RankDataOther(popularity = "本周订阅：xxxx"),
                    RankDataOther(popularity = "本周订阅：xxxx"),
                    RankDataOther(popularity = "本周订阅：xxxx")
                )
            ),
            RankData(rankPosition = 4, rankDataOther = RankDataOther(popularity = "本周订阅：xxxx")),
            RankData(rankPosition = 5, rankDataOther = RankDataOther(popularity = "本周订阅：xxxx")),
            RankData(rankPosition = 6, rankDataOther = RankDataOther(popularity = "本周订阅：xxxx")),
            RankData(rankPosition = 7, rankDataOther = RankDataOther(popularity = "本周订阅：xxxx")),
            RankData(rankPosition = 8, rankDataOther = RankDataOther(popularity = "本周订阅：xxxx")),
            RankData(rankPosition = 9, rankDataOther = RankDataOther(popularity = "本周订阅：xxxx")),
            RankData(
                rankPosition = 10, rankDataOther = RankDataOther(popularity = "本周订阅：xxxx")
            ),
        )
    }

    suspend fun getRankRoastData(): List<RankData> {
        delay(2000)
        return listOf(
            RankData(
                listOf(
                    RankDataOther(popularity = "本周吐槽：xxxx"),
                    RankDataOther(popularity = "本周吐槽：xxxx"),
                    RankDataOther(popularity = "本周吐槽：xxxx")
                )
            ),
            RankData(rankPosition = 4, rankDataOther = RankDataOther(popularity = "本周吐槽：xxxx")),
            RankData(rankPosition = 5, rankDataOther = RankDataOther(popularity = "本周吐槽：xxxx")),
            RankData(rankPosition = 6, rankDataOther = RankDataOther(popularity = "本周吐槽：xxxx")),
            RankData(rankPosition = 7, rankDataOther = RankDataOther(popularity = "本周吐槽：xxxx")),
            RankData(rankPosition = 8, rankDataOther = RankDataOther(popularity = "本周吐槽：xxxx")),
            RankData(rankPosition = 9, rankDataOther = RankDataOther(popularity = "本周吐槽：xxxx")),
            RankData(
                rankPosition = 10, rankDataOther = RankDataOther(popularity = "本周吐槽：xxxx")
            ),
        )
    }

    suspend fun getRankPopularityData(): List<RankData> {
        delay(2000)
        return listOf(
            RankData(listOf(RankDataOther(), RankDataOther(), RankDataOther())),
            RankData(rankPosition = 4),
            RankData(rankPosition = 5),
            RankData(rankPosition = 6),
            RankData(rankPosition = 7),
            RankData(rankPosition = 8),
            RankData(rankPosition = 9),
            RankData(rankPosition = 10),
        )
    }

    suspend fun getSortData(): List<SortData> {
        delay(2000)
        return listOf(
            SortData(),
            SortData(),
            SortData(),
            SortData(),
            SortData(),
            SortData(),
            SortData(),
            SortData(),
            SortData(),
            SortData(),
            SortData(),
            SortData(),
            SortData(),
            SortData(),
            SortData(),
            SortData(),
            SortData(),
            SortData(),
            SortData(),
            SortData(),
            SortData(),
            SortData(),
            SortData(),
            SortData(),
            SortData(),
            SortData(),
            SortData(),
        )
    }

    suspend fun getSubscribeData(page: Int, subscribeOrder: SortType): List<SubscribeData> {
        delay(2000)
        return when (subscribeOrder) {
            SortType.subscribe_order -> {
                listOf(
                    SubscribeData(name = "001"),
                    SubscribeData(name = "002"),
                    SubscribeData(name = "003"),
                    SubscribeData(name = "004")
                )
            }

            SortType.update_time -> {
                listOf(
                    SubscribeData(name = "002"),
                    SubscribeData(name = "001"),
                    SubscribeData(name = "004"),
                    SubscribeData(name = "003")
                )
            }

            SortType.recent_read -> {
                listOf(
                    SubscribeData(name = "003"),
                    SubscribeData(name = "002"),
                    SubscribeData(name = "001"),
                    SubscribeData(name = "004")
                )
            }
        }
    }

}