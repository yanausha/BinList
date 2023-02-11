package com.example.binlist.data.repository

import com.example.binlist.data.mapper.BinMapper
import com.example.binlist.data.network.ApiService
import com.example.binlist.domain.BinRepository
import com.example.binlist.domain.entity.BinInfo
import javax.inject.Inject

class BinRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: BinMapper
): BinRepository {

    override suspend fun getBinInfo(bin: String): BinInfo {
        return mapper.mapDtoToEntity(apiService.getBinList(bin))
    }
}