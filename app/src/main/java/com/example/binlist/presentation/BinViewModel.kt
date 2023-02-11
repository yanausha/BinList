package com.example.binlist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.binlist.domain.usecase.GetBinInfoUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class BinViewModel @Inject constructor(
    private val getBinInfoUseCase: GetBinInfoUseCase
) : ViewModel() {

    fun getBinInfo(bin: String) {
        viewModelScope.launch {
            getBinInfoUseCase(bin)
        }
    }
}
