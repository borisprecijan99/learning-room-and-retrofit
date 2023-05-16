package com.example.learningroomandretrofit.data

import javax.inject.Inject

class QuoteRepository @Inject constructor(private val quoteApi: QuoteApi) {
    suspend fun getRandomQuote(): Quote {
        return quoteApi.getRandomQuote()
    }
}