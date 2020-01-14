package com.sunragav.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sunragav.domain.models.DomainCatalog
import com.sunragav.domain.usecases.GetCatalog
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class CatalogViewModel @Inject constructor(val getCatalog: GetCatalog) : ViewModel() {

    val uiState: LiveData<UiState>
        get() = _uiState
    val catalogLiveData: LiveData<List<DomainCatalog>>
        get() = _catalogLiveData

    //////////////////////////////////////////////////////////////////////
    private val _uiState = MutableLiveData<UiState>()
    private val _catalogLiveData = MutableLiveData<List<DomainCatalog>>()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _uiState.postValue(UiState.Error(throwable))
    }

    /////////////////////////////////////////////////////////////////////
    init {
        viewModelScope.launch(exceptionHandler) {
            _uiState.postValue(UiState.Loading)
            _catalogLiveData.postValue(getCatalog())
            _uiState.postValue(UiState.Complete)
        }
    }

}