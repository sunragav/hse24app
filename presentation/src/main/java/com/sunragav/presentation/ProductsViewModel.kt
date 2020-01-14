package com.sunragav.presentation

import androidx.lifecycle.*
import com.sunragav.domain.models.DomainProduct
import com.sunragav.domain.usecases.GetProducts
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject


class ProductsViewModel @Inject constructor(private val getProducts: GetProducts) : ViewModel() {

    val uiState: LiveData<UiState>
        get() = _uiState
    val productsLiveData: LiveData<List<DomainProduct>>
        get() = _productsLiveData

    val productsByCategoryLiveData = MutableLiveData<Int>()


    //Private
    private val _uiState = MutableLiveData<UiState>()
    private val _productsLiveData = MediatorLiveData<List<DomainProduct>>()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _uiState.postValue(UiState.Error(throwable))
    }


    //init
    init {
        _productsLiveData.addSource(productsByCategoryLiveData) {
            viewModelScope.launch(exceptionHandler) {
                _uiState.postValue(UiState.Loading)
                _productsLiveData.postValue(getProducts(it, 0))
                _uiState.postValue(UiState.Complete)
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
}