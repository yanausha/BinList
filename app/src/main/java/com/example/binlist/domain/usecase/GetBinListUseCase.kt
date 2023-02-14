package com.example.binlist.domain.usecase

import com.example.binlist.domain.BinRepository
import javax.inject.Inject

class GetBinListUseCase @Inject constructor(
    private val repository: BinRepository
) {

    operator fun invoke() = repository.getBinList()
}