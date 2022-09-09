package com.example.quantumapp.network.repository

import com.example.quantumapp.network.NewsApi

class MainRepository constructor(
    private val newsApi: NewsApi
) {
    suspend fun getAllNews(topic: String, apiKey: String) = newsApi.getNews(topic, apiKey)
}