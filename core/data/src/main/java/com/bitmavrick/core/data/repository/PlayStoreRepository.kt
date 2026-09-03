package com.bitmavrick.core.data.repository

import com.bitmavrick.core.model.PlayStoreApp

interface PlayStoreRepository {
    suspend fun searchApps(
        keyword: String,
        country: String = "us",
        language: String = "en",
        limit: Int = 20
    ): List<PlayStoreApp>
}