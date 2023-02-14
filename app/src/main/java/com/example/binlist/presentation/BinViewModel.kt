package com.example.binlist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.binlist.domain.entity.BinInfo
import com.example.binlist.domain.usecase.AddBinInfoUseCase
import com.example.binlist.domain.usecase.GetBinInfoUseCase
import com.example.binlist.domain.usecase.GetBinItemUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class BinViewModel @Inject constructor(
    private val getBinInfoUseCase: GetBinInfoUseCase,
    private val addBinInfoUseCase: AddBinInfoUseCase,
    private val getBinItemUseCase: GetBinItemUseCase
) : ViewModel() {

    private val _binInfo = MutableLiveData<BinInfo>()
    val binInfo: LiveData<BinInfo>
        get() = _binInfo

    fun getBinInfo(bin: String) {
        viewModelScope.launch {
            _binInfo.value = getBinInfoUseCase(bin)
            val binItem = getBinItemUseCase(bin, binInfo.value!!)
            addBinInfoUseCase(binItem)
        }
    }
}
