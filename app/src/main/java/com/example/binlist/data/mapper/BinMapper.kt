package com.example.binlist.data.mapper

import com.example.binlist.data.network.models.BankDto
import com.example.binlist.data.network.models.BinInfoDto
import com.example.binlist.data.network.models.CountryDto
import com.example.binlist.data.network.models.NumberDto
import com.example.binlist.domain.entity.Bank
import com.example.binlist.domain.entity.BinInfo
import com.example.binlist.domain.entity.Country
import com.example.binlist.domain.entity.Number
import javax.inject.Inject

class BinMapper @Inject constructor() {

    fun mapDtoToEntity(dto: BinInfoDto?) = BinInfo(
        mapDtoToEntity(dto?.bank),
        dto?.brand,
        mapDtoToEntity(dto?.country),
        mapDtoToEntity(dto?.number),
        dto?.prepaid,
        dto?.scheme,
        dto?.type
    )

    private fun mapDtoToEntity(dto: BankDto?): Bank? {
        return if (dto != null) Bank(
            dto.city,
            dto.name,
            dto.phone,
            dto.url
        ) else null
    }

    private fun mapDtoToEntity(dto: CountryDto?): Country? {
        return if (dto != null) Country(
            dto.alpha2,
            dto.currency,
            dto.emoji,
            dto.latitude,
            dto.longitude,
            dto.name,
            dto.numeric
        ) else null
    }

    private fun mapDtoToEntity(dto: NumberDto?): Number? {
        return if (dto != null) Number(
            dto.length,
            dto.luhn
        ) else null
    }
}