package com.example.binlist.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.binlist.data.database.BinInfoDao
import com.example.binlist.data.mapper.BinMapper
import com.example.binlist.data.network.ApiService
import com.example.binlist.domain.BinRepository
import com.example.binlist.domain.entity.BinInfo
import com.example.binlist.domain.entity.BinItem
import javax.inject.Inject

class BinRepositoryImpl @Inject constructor(
    private val binInfoDao: BinInfoDao,
    private val apiService: ApiService,
    private val mapper: BinMapper
): BinRepository {

    override suspend fun getBinInfo(bin: String): BinInfo? {
        val response = apiService.getBinInfo(bin)
        return if (response.code() == 404) {
            null
        } else {
            mapper.mapDtoToEntity(response.body())
        }
    }

    override suspend fun getBinItem(bin: String, binInfo: BinInfo): BinItem {
        return BinItem(bin, binInfo.country?.emoji, binInfo.country?.name, binInfo.bank?.name)
    }

    override suspend fun addBinInfo(binItem: BinItem?) {
        binInfoDao.addBinInfo(binItem?.let { mapper.mapEntityToDbModel(it) })
    }

    override fun getBinList(): LiveData<List<BinItem>> {
        return Transformations.map(binInfoDao.getBinList()) {
            it.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    override suspend fun deleteBinItem(binItem: BinItem) {
        binInfoDao.deleteBinInfo(binItem.bin)
    }
}