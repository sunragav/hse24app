package com.sunragav.presentation

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sunragav.domain.models.DomainProduct
import com.sunragav.domain.usecases.GetProducts
import com.sunragav.presentation.products.paging.DomainProductsDataSource
import kotlinx.coroutines.launch
import javax.inject.Inject


class ProductsViewModel @Inject constructor(private val getProducts: GetProducts) : ViewModel() {

    val uiState: LiveData<UiState>
        get() = _uiState
    val productsLiveData: LiveData<PagedList<DomainProduct>>
        get() = Transformations.switchMap(_productsMediatorLiveData) { it }

    val productsByCategoryLiveData = MutableLiveData<Int>()

    //Private
    private val _productsMediatorLiveData = MediatorLiveData<LiveData<PagedList<DomainProduct>>>()

    private val _uiState = MutableLiveData<UiState>()

    //init
    init {
        _productsMediatorLiveData.addSource(productsByCategoryLiveData) { categoryId ->
            viewModelScope.launch {
                val productsDataSourceFactory =
                    DomainProductsDataSource.Factory(
                        categoryId,
                        getProducts,
                        viewModelScope,
                        _uiState
                    )
                _productsMediatorLiveData.postValue(
                    LivePagedListBuilder(
                        productsDataSourceFactory,
                        PAGE_SIZE
                    ).build()
                )
            }
        }
    }

    /////////////////////////////////////////////////////////////////////
    //ViewModelFactory

    class Factory(
        private val getProducts: GetProducts
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ProductsViewModel::class.java)) {
                return ProductsViewModel(getProducts) as T
            }
            throw IllegalArgumentException("ViewModel not found.")
        }
    }

    /////////////////////////////////////////////////////////////////////

    companion object {
        const val PAGE_SIZE = 24
    }
}