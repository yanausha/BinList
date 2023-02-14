package com.example.binlist.domain

import com.example.binlist.domain.entity.BinInfo
import com.example.binlist.domain.entity.BinItem

interface BinRepository {

    suspend fun getBinInfo(bin: String): BinInfo
    suspend fun getBinItem(bin: String, binInfo: BinInfo): BinItem
    suspend fun addBinInfo(binItem: BinItem)
}