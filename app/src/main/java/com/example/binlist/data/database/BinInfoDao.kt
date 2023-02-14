package com.example.binlist.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BinInfoDao {

    @Query("SELECT * FROM binList")
    fun getBinList(): LiveData<List<BinInfoDbModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBinInfo(binInfo: BinInfoDbModel)

    @Query("DELETE FROM binList WHERE bin=:bin")
    suspend fun deleteBinInfo(bin: String)
}
