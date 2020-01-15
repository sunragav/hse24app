package com.sunragav.presentation

import androidx.lifecycle.*
import com.sunragav.domain.models.DomainProduct
import com.sunragav.presentation.CartViewModel.CartOperation.Add
import com.sunragav.presentation.CartViewModel.CartOperation.Remove
import javax.inject.Inject


class CartViewModel @Inject constructor() : ViewModel() {

    sealed class CartOperation {
        class Add : CartOperation()
        class Remove : CartOperation()
    }

    class Action(val type: CartOperation, val domainProduct: DomainProduct)

    val cartLiveData = MutableLiveData<Action>()

    val cartListLiveData: LiveData<MutableMap<DomainProduct, Int>>
        get() = _cartMediatorLiveData

    val cartCount: LiveData<Int>
        get() = _cartCountMediatorLiveData

    val totalAmount: LiveData<Float>
        get() = _cartAmountMediatorLiveData


    private val _cartMediatorLiveData = MediatorLiveData<MutableMap<DomainProduct, Int>>()
    private val _cartCountMediatorLiveData = MediatorLiveData<Int>()
    private val _cartAmountMediatorLiveData = MediatorLiveData<Float>()


    //init
    init {
        _cartMediatorLiveData.addSource(cartLiveData) { productAction ->
            if (_cartMediatorLiveData.value == null) _cartMediatorLiveData.value =
                mutableMapOf()
            val map = _cartMediatorLiveData.value!!
            val prod = productAction.domainProduct
            val count = map[prod]
            when (productAction.type) {
                is Add -> {
                    if (count == null) map[prod] = 1
                    else map[prod] = count + 1
                }
                is Remove -> {
                    if (count == null) map[prod] = 0
                    else if (count > 1)
                        map[prod] = count - 1
                }
            }
        }
        _cartCountMediatorLiveData.addSource(_cartMediatorLiveData) {
            _cartCountMediatorLiveData.postValue(it.size)
        }
        _cartAmountMediatorLiveData.addSource(_cartMediatorLiveData) { productMap ->
            _cartAmountMediatorLiveData.postValue(productMap.map { it.key.productPrice.split(" ")[0].toFloat() * it.value }.sum())
        }
    }

    /////////////////////////////////////////////////////////////////////
    //ViewModelFactory

    class Factory : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
                return CartViewModel() as T
            }
            throw IllegalArgumentException("ViewModel not found.")
        }
    }

    /////////////////////////////////////////////////////////////////////
}