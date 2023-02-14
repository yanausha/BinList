package com.example.binlist.domain.usecase

import com.example.binlist.domain.BinRepository
import com.example.binlist.domain.entity.BinInfo
import javax.inject.Inject

class GetBinItemUseCase @Inject constructor(private val repository: BinRepository) {

    suspend operator fun invoke(bin: String, binInfo: BinInfo) = repository.getBinItem(bin, binInfo)
}