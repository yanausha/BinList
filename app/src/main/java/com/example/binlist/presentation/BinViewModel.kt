package com.example.binlist.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
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

    private val _binInfo = MutableLiveData<BinInfo?>()
    val binInfo: LiveData<BinInfo?>
        get() = _binInfo

    fun getBinInfo(bin: String) {
        viewModelScope.launch {
            _binInfo.value = getBinInfoUseCase(bin)
             binInfo.value?.let {
                val binItem = getBinItemUseCase(bin, it)
                addBinInfoUseCase(binItem)
            }
        }
    }

    fun deleteBinItem(binItem: BinItem) {
        viewModelScope.launch {
            deleteBinItemUseCase(binItem)
        }
    }

    fun useMap(context: Context) {
        val longitude = binInfo.value?.country?.longitude
        val latitude = binInfo.value?.country?.latitude
        if (longitude != null && latitude != null) {
            val mapUri = Uri.parse("geo:$latitude,$longitude")
            val intent = Intent(Intent.ACTION_VIEW, mapUri)
            context.startActivity(intent)
        }
    }

    fun useCall(context: Context) {
        val phoneNumber = binInfo.value?.bank?.phone
        if (phoneNumber != null) {
            val callUri = Uri.parse("tel:$phoneNumber")
            val intent = Intent(Intent.ACTION_DIAL, callUri)
            context.startActivity(intent)
        }
    }

    fun useWebsite(context: Context) {
        val website = binInfo.value?.bank?.url
        if (website != null) {
            val webUri = Uri.parse("http://$website")
            val intent = Intent(Intent.ACTION_VIEW, webUri)
            context.startActivity(intent)
        }
    }
}
