package com.example.apipractice.data.repository

import com.example.apipractice.data.model.Animes
import retrofit2.http.GET

interface ApiRepository {

    //method to call endpoint for all animes from API
    suspend fun getAllAnimes(): Animes
}