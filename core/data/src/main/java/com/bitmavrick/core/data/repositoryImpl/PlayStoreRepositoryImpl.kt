package com.bitmavrick.core.data.repositoryImpl

import com.bitmavrick.core.data.repository.PlayStoreRepository
import com.bitmavrick.core.model.PlayStoreApp
import com.chaquo.python.Python

class PlayStoreRepositoryImpl : PlayStoreRepository {

    private val python: Python by lazy {
        Python.getInstance()
    }

    override suspend fun searchApps(
        keyword: String,
        country: String,
        language: String,
        limit: Int
    ): List<PlayStoreApp> {

        val module = python.getModule("playstore_scraper")

        val result = module.callAttr(
            "search_apps",
            keyword,
            country,
            language,
            limit
        )

        return result.asList().map { item ->
            PlayStoreApp(
                rank = item["rank"]?.toInt() ?: -1,
                title = item["title"].toString(),
                packageName = item["package"].toString()
            )
        }
    }
}