package com.sunragav.presentation

import androidx.lifecycle.*
import com.sunragav.domain.models.DomainCatalog
import com.sunragav.domain.usecases.GetCatalog
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class CatalogViewModel @Inject constructor(private val getCatalog: GetCatalog) : ViewModel() {

    val uiState: LiveData<UiState>
        get() = _uiState
    val catalogLiveData: LiveData<List<DomainCatalog>>
        get() = _catalogLiveData


    //Private
    private val _uiState = MutableLiveData<UiState>()
    private val _catalogLiveData = MutableLiveData<List<DomainCatalog>>()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _uiState.postValue(UiState.Error(throwable))
    }


    //init
    init {
        viewModelScope.launch(exceptionHandler) {
            _uiState.postValue(UiState.Loading)
            _catalogLiveData.postValue(getCatalog())
            _uiState.postValue(UiState.Complete)
        }
    }

    /////////////////////////////////////////////////////////////////////
    //ViewModelFactory

    class Factory(
        private val getCatalog: GetCatalog
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CatalogViewModel::class.java)) {
                return CatalogViewModel(getCatalog) as T
            }
            throw IllegalArgumentException("ViewModel not found.")
        }
    }

    /////////////////////////////////////////////////////////////////////
}