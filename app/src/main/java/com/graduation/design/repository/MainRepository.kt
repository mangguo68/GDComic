package com.graduation.design.repository

import android.accounts.NetworkErrorException
import com.graduation.design.model.pojo.BannerData
import com.graduation.design.model.pojo.RankData
import com.graduation.design.model.pojo.RankType
import com.graduation.design.model.pojo.RecommendData
import com.graduation.design.model.pojo.SortData
import com.graduation.design.model.pojo.SortType
import com.graduation.design.model.pojo.SubscribeData
import com.graduation.design.model.result.Results

class MainRepository {
    private val dataSource = DmzjDataSource()
    suspend fun getBannerData(): Results<List<BannerData>> {
        return try {
            Results.succeed(dataSource.getBannerData())
        } catch (e: NetworkErrorException) {
            Results.failed(listOf())
        }
    }

    suspend fun getHomePageData(data: Int): Results<List<RecommendData>> {
        return when (data) {
            0 -> {
                try {
                    Results.succeed(dataSource.getSubscribeData())
                } catch (e: NetworkErrorException) {
                    Results.failed(emptyList())
                }
            }

            1 -> {
                try {
                    Results.succeed(dataSource.getRecentUpdateData())
                } catch (e: NetworkErrorException) {
                    Results.failed(emptyList())
                }

            }

            else -> {
                try {
                    Results.succeed(dataSource.getFineRecommendData())
                } catch (e: NetworkErrorException) {
                    Results.failed(emptyList())
                }
            }
        }
    }

    suspend fun getRankData(rankType: RankType): Results<List<RankData>> {
        return when (rankType) {
            RankType.popularity_rank -> {
                try {
                    Results.succeed(dataSource.getRankPopularityData())
                } catch (e: NetworkErrorException) {
                    Results.failed(emptyList())
                }
            }

            RankType.roast_rank -> {
                try {
                    Results.succeed(dataSource.getRankRoastData())
                } catch (e: NetworkErrorException) {
                    Results.failed(emptyList())
                }
            }

            else -> {
                try {
                    Results.succeed(dataSource.getRankSubscribeData())
                } catch (e: NetworkErrorException) {
                    Results.failed(emptyList())
                }
            }
        }
    }

    suspend fun getSortData(params: MutableList<Int>): Results<List<SortData>> {
        //做了很多的业务逻辑...
        return try {
            Results.succeed(dataSource.getSortData())
        } catch (e: NetworkErrorException) {
            Results.failed(emptyList())
        }
    }

    suspend fun getSubscribeData(
        page: Int, subscribeOrder: SortType
    ): Results<List<SubscribeData>> {
        return try {
            Results.succeed(
                dataSource.getSubscribeData(page, subscribeOrder)
            )
        } catch (e: NetworkErrorException) {
            Results.failed(emptyList())
        }
    }

}