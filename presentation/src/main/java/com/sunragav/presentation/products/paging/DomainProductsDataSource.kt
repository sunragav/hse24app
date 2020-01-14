package com.sunragav.presentation.products.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.sunragav.domain.models.DomainProduct
import com.sunragav.domain.usecases.GetProducts
import com.sunragav.presentation.UiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DomainProductsDataSource(
    private val categoryId: Int,
    private val getProducts: GetProducts,
    private val scope: CoroutineScope
) : PageKeyedDataSource<Int, DomainProduct>() {

    val getDomainProductsState: LiveData<UiState>
        get() = _getDomainProductsState

    private val _getDomainProductsState = MutableLiveData<UiState>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _getDomainProductsState.postValue(UiState.Error(throwable))
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, DomainProduct>
    ) {
        scope.launch(exceptionHandler) {
            _getDomainProductsState.postValue(UiState.Loading)
            val products = getProducts(categoryId, 0)
            callback.onResult(products, null, 2)
            _getDomainProductsState.postValue(UiState.Complete)
            if (products.isEmpty()) {
                _getDomainProductsState.postValue(UiState.Empty)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, DomainProduct>) {
        scope.launch(exceptionHandler) {
            callback.onResult(getProducts(categoryId, params.key), params.key + 1)
            _getDomainProductsState.postValue(UiState.Complete)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, DomainProduct>) {
        scope.launch(exceptionHandler) {
            callback.onResult(getProducts(categoryId, params.key), params.key - 1)
            _getDomainProductsState.postValue(UiState.Complete)
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////
    //DomainProductsDataSource.Factory
    class Factory(
        private val categoryId: Int,
        private val getProducts: GetProducts,
        private val scope: CoroutineScope
    ) : DataSource.Factory<Int, DomainProduct>() {
        val sourceLiveData = MutableLiveData<DomainProductsDataSource>()
        private lateinit var latestSource: DomainProductsDataSource

        override fun create(): DataSource<Int, DomainProduct> {
            latestSource = DomainProductsDataSource(categoryId, getProducts, scope)
            sourceLiveData.postValue(latestSource)
            return latestSource
        }
    }
}
