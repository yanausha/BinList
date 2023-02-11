package com.example.binlist.di

import androidx.lifecycle.ViewModel
import com.example.binlist.presentation.BinViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @BinMapKey(BinViewModel::class)
    @Binds
    fun bindViewModel(impl: BinViewModel): ViewModel
}