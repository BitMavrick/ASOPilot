package com.bitmavrick.asopilot.data.repository

import com.bitmavrick.asopilot.data.model.PlayStoreApp

interface PlayStoreRepository {
    suspend fun searchApps(
        keyword: String,
        country: String = "us",
        language: String = "en",
        limit: Int = 20
    ): List<PlayStoreApp>
}