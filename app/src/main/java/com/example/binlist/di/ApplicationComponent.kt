package com.example.binlist.di

import android.app.Application
import com.example.binlist.presentation.BinDetailInfoFragment
import com.example.binlist.presentation.MainFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModelModule::class])
interface ApplicationComponent {

    fun inject(fragment: MainFragment)
    fun inject(fragment: BinDetailInfoFragment)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}
