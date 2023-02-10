package com.example.binlist.data.network

import com.example.binlist.data.network.models.BinInfoDto
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET
    suspend fun getBinList(@Url bin: String): BinInfoDto
}
