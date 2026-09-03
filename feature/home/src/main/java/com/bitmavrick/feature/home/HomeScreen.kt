package com.bitmavrick.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bitmavrick.core.data.repositoryImpl.PlayStoreRepositoryImpl
import com.bitmavrick.core.model.PlayStoreApp
import kotlinx.coroutines.launch

@Composable
fun HomeScreen() {
    val scope = rememberCoroutineScope()

    var isLoading by remember { mutableStateOf(false) }
    var results by remember { mutableStateOf<List<PlayStoreApp>>(emptyList()) }
    var error by remember { mutableStateOf<String?>(null) }

    val repository = remember {
        PlayStoreRepositoryImpl()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = {
                scope.launch {
                    isLoading = true
                    error = null

                    try {
                        results = repository.searchApps(
                            keyword = "screen flashlight",
                            country = "us",
                            language = "en",
                            limit = 20
                        )
                    } catch (e: Exception) {
                        error = e.stackTraceToString()
                    } finally {
                        isLoading = false
                    }
                }
            },
            enabled = !isLoading
        ) {
            Text(
                if (isLoading) "Searching..." else "Test Scraper"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        error?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error
            )
        }

        LazyColumn {
            items(results) { app ->
                Column(
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Text(
                        text = "${app.rank}. ${app.title}",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = app.packageName,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}