package com.sunragav.hse24app.di

import com.sunragav.domain.usecases.GetCatalog
import com.sunragav.domain.usecases.GetProducts
import com.sunragav.presentation.CatalogViewModel
import com.sunragav.presentation.ProductsViewModel
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {
    @Provides
    fun provideCatalogViewModelFactory(
        getCatalog: GetCatalog
    ) = CatalogViewModel.Factory(getCatalog)

    @Provides
    fun provideProductsViewModelFactory(
        getProducts: GetProducts
    ) = ProductsViewModel.Factory(getProducts)
}