package com.task.wikisearch.data.repository

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.task.wikisearch.domain.model.internal.WikiItemInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class ThumbnailDownloadManager(private val context: Context) {
    val downloadedThumbnails = mutableStateOf<Map<String, String>>(emptyMap())

    private val client = OkHttpClient()
    private var isDownloading = false

    suspend fun downloadThumbnails(items: List<WikiItemInfo>, batchSize: Int = 1) {
        if (isDownloading) return
        isDownloading = true

        var startIndex = 0
        while (startIndex < items.size) {
            val endIndex = minOf(startIndex + batchSize, items.size)
            val batch = items.subList(startIndex, endIndex)

            downloadBatch(batch)

            startIndex = endIndex
        }

        isDownloading = false
    }

    private suspend fun downloadBatch(batch: List<WikiItemInfo>) = withContext(Dispatchers.IO) {
        val downloadedThumbnailPaths = mutableMapOf<String, String>()
        delay(1000) //for testing purposes /mimic slow network/
        for (item in batch) {
            try {
                val thumbnailUrl = item.thumbnail.url
                if (thumbnailUrl.isNullOrEmpty()) continue
                val fileName = "${item.key}_${item.key}.jpg"
                val file = File(context.cacheDir, fileName)

                val request = Request.Builder().url(thumbnailUrl).build()
                val response = client.newCall(request).execute()

                if (response.isSuccessful) {
                    response.body.let { body ->
                        FileOutputStream(file).use { outputStream ->
                            outputStream.write(body.bytes())
                        }
                        downloadedThumbnailPaths[item.key] = file.absolutePath
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        downloadedThumbnails.value += downloadedThumbnailPaths
    }
}