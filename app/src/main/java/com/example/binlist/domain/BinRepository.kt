package com.example.binlist.domain

import androidx.lifecycle.LiveData
import com.example.binlist.domain.entity.BinInfo
import com.example.binlist.domain.entity.BinItem

interface BinRepository {

    suspend fun getBinInfo(bin: String): BinInfo
    suspend fun getBinItem(bin: String, binInfo: BinInfo): BinItem
    suspend fun addBinInfo(binItem: BinItem)
    fun getBinList(): LiveData<List<BinItem>>
    suspend fun deleteBinItem(binItem: BinItem)
}