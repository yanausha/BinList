package com.example.binlist.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "binList")
data class BinInfoDbModel(
    @PrimaryKey
    val bin: String,
    val smile: String?,
    val country: String?,
    val bank: String?
)
