package com.bitmavrick.asopilot.data.repositoryImpl

import com.bitmavrick.asopilot.data.model.PlayStoreApp
import com.bitmavrick.asopilot.data.repository.PlayStoreRepository
import com.chaquo.python.Python
import com.chaquo.python.PyObject

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