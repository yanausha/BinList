package com.example.binlist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.binlist.domain.entity.BinInfo
import com.example.binlist.domain.entity.BinItem
import com.example.binlist.domain.usecase.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class BinViewModel @Inject constructor(
    private val getBinInfoUseCase: GetBinInfoUseCase,
    private val addBinInfoUseCase: AddBinInfoUseCase,
    private val getBinItemUseCase: GetBinItemUseCase,
    private val getBinListUseCase: GetBinListUseCase,
    private val deleteBinItemUseCase: DeleteBinItemUseCase
) : ViewModel() {

    val binItem = getBinListUseCase()

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

    fun deleteBinItem(binItem: BinItem) {
        viewModelScope.launch {
            deleteBinItemUseCase(binItem)
        }
    }
}
