package com.example.apipractice.data.repository

import com.example.apipractice.data.api.ApiEndpoints
import com.example.apipractice.data.model.Animes
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(private val apiEndpoints: ApiEndpoints):ApiRepository {
    override suspend fun getAllAnimes(): Animes = apiEndpoints.getAllAnimes()
}