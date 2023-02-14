package com.example.binlist.data.mapper

import com.example.binlist.data.database.BinInfoDbModel
import com.example.binlist.data.network.models.BankDto
import com.example.binlist.data.network.models.BinInfoDto
import com.example.binlist.data.network.models.CountryDto
import com.example.binlist.data.network.models.NumberDto
import com.example.binlist.domain.entity.*
import com.example.binlist.domain.entity.Number
import javax.inject.Inject

class BinMapper @Inject constructor() {

    fun mapEntityToDbModel(binItem: BinItem) = BinInfoDbModel(
        binItem.bin,
        binItem.emoji,
        binItem.country,
        binItem.bank
    )

    fun mapDbModelToEntity(db: BinInfoDbModel) = BinItem(
        db.bin,
        db.smile,
        db.country,
        db.bank
    )

    fun mapDtoToEntity(dto: BinInfoDto?) = BinInfo(
        mapDtoToEntity(dto?.bank),
        dto?.brand,
        mapDtoToEntity(dto?.country),
        mapDtoToEntity(dto?.number),
        dto?.prepaid,
        dto?.scheme,
        dto?.type
    )

    private fun mapDtoToEntity(dto: BankDto?) = Bank(
        dto?.city,
        dto?.name,
        dto?.phone,
        dto?.url
    )

    private fun mapDtoToEntity(dto: CountryDto?) = Country(
        dto?.alpha2,
        dto?.currency,
        dto?.emoji,
        dto?.latitude,
        dto?.longitude,
        dto?.name,
        dto?.numeric
    )

    private fun mapDtoToEntity(dto: NumberDto?) = Number(
        dto?.length,
        dto?.luhn
    )
}