package com.sunragav.hse24app.di

import android.app.Application
import com.sunragav.hse24app.HSE24App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        DataModule::class,
        PresentationModule::class,
        AppModule::class
    ]
)
interface AppComponent : AndroidInjector<HSE24App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(app: HSE24App)
}