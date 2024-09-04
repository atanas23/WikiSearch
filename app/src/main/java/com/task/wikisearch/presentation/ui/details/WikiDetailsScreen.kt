package com.task.wikisearch.presentation.ui.details

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun WikiDetailsScreen(key: String) {
    val wikiDetailsUrl = "https://en.wikipedia.org/wiki/${key}"
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                loadUrl(wikiDetailsUrl)
            }
        },
        update = { webView ->
            webView.loadUrl(wikiDetailsUrl)
        },
    )
}