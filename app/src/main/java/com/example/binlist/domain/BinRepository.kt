package com.example.binlist.domain

import com.example.binlist.domain.entity.BinInfo

interface BinRepository {

    suspend fun getBinInfo(bin: String): BinInfo
}