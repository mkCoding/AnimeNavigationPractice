package com.example.apipractice.data.api

import com.example.apipractice.data.model.Animes
import retrofit2.http.GET

interface ApiEndpoints {

    @GET(ApiDetails.ENDPOINT_ANIMES)
    suspend fun getAllAnimes(): Animes
}