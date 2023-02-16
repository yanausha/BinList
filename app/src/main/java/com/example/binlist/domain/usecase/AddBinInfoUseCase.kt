package com.example.binlist.domain.usecase

import com.example.binlist.domain.BinRepository
import com.example.binlist.domain.entity.BinItem
import javax.inject.Inject

class AddBinInfoUseCase @Inject constructor(private val repository: BinRepository) {

    suspend operator fun invoke(binItem: BinItem?) = repository.addBinInfo(binItem)
}