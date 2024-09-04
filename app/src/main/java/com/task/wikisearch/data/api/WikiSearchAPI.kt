package com.task.wikisearch.data.api

import com.task.wikisearch.domain.model.external.WikiListExternal
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

interface WikiSearchAPI {
    @GET("wikipedia/en/search/title")
    suspend fun getWikiList(
        @Query("q") query: String,
        @Query("limit") limit: Int): WikiListExternal
}

object RetrofitInstance {
    private const val BASE_URL = "https://api.wikimedia.org/core/v1/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val api: WikiSearchAPI by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WikiSearchAPI::class.java)
    }
}


