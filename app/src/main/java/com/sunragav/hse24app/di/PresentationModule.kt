package com.sunragav.hse24app.di

import com.sunragav.domain.usecases.GetCatalog
import com.sunragav.presentation.CatalogViewModel
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {
    @Provides
    fun provideCatalogViewModelFactory(
        getCatalog: GetCatalog
    ) = CatalogViewModel.Factory(getCatalog)
}