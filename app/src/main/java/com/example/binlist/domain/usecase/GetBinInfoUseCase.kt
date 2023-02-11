package com.example.binlist.domain.usecase

import com.example.binlist.domain.BinRepository
import javax.inject.Inject

class GetBinInfoUseCase @Inject constructor(private val repository: BinRepository) {

    suspend operator fun invoke(bin: String) = repository.getBinInfo(bin)
}
