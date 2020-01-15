package com.sunragav.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.sunragav.domain.models.DomainProduct
import com.sunragav.presentation.CartOperation.Add
import com.sunragav.presentation.CartOperation.Remove
import javax.inject.Inject
import kotlin.collections.set

sealed class CartOperation {
    object Add : CartOperation()
    object Remove : CartOperation()
}

class Action(val type: CartOperation, val domainProduct: DomainProduct)

class CartViewModel @Inject constructor() : ViewModel() {
    fun post(productAction: Action) {
        val map = _cartMediatorLiveData.value!!
        val prod = productAction.domainProduct
        val count = map[prod]
        when (productAction.type) {
            is Add -> {
                if (count == null) map[prod] = 1
                else map[prod] = count + 1
            }
            is Remove -> {
                if (count == 1)
                    map.remove(prod)
                else if (count != null)
                    map[prod] = count - 1

            }
        }
        _cartCountMediatorLiveData.postValue(map.size)
        _cartAmountMediatorLiveData.postValue(
            map.map { it.key.productPrice.split(" ")[0].toFloat() * it.value }.sum()
        )
    }


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
        _cartMediatorLiveData.value = mutableMapOf()
    }
}