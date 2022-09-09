package com.example.quantumapp.models

data class News(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)