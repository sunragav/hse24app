package com.sunragav.presentation

import androidx.lifecycle.*
import com.sunragav.domain.models.DomainPDP
import com.sunragav.domain.usecases.GetPDP
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class PDPViewModel @Inject constructor(private val getPDP: GetPDP) : ViewModel() {

    val uiState: LiveData<UiState>
        get() = _uiState
    val pdpLiveData: LiveData<DomainPDP>
        get() = _pdpMediatorLiveData
    val pdpBySkuLiveData = MutableLiveData<String>()

    //Private
    private val _uiState = MutableLiveData<UiState>()
    private val _pdpMediatorLiveData = MediatorLiveData<DomainPDP>()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _uiState.postValue(UiState.Error(throwable))
    }


    //init
    init {
        _pdpMediatorLiveData.addSource(pdpBySkuLiveData) { sku ->
            viewModelScope.launch(exceptionHandler) {
                _uiState.postValue(UiState.Loading)
                _pdpMediatorLiveData.postValue(getPDP(sku))
                _uiState.postValue(UiState.Complete)
            }
        }
    }

    /////////////////////////////////////////////////////////////////////
    //ViewModelFactory

    class Factory(
        private val getPDP: GetPDP
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PDPViewModel::class.java)) {
                return PDPViewModel(getPDP) as T
            }
            throw IllegalArgumentException("ViewModel not found.")
        }
    }

    /////////////////////////////////////////////////////////////////////
}