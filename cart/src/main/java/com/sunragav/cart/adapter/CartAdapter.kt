package com.sunragav.cart.adapter

import android.annotation.SuppressLint
import android.net.Uri
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sunragav.android_core.adapters.BaseListAdapter
import com.sunragav.android_core.animations.animate
import com.sunragav.android_core.deeplinks.DeepLinks
import com.sunragav.android_core.deeplinks.navigateUriWithDefaultOptions
import com.sunragav.cart.R
import com.sunragav.domain.models.DomainProduct
import com.sunragav.presentation.Action
import com.sunragav.presentation.CartOperation
import com.sunragav.presentation.CartViewModel

class CartAdapter(private val cartViewModel: CartViewModel) : BaseListAdapter<DomainProduct>(
    itemsSame = { old, new -> old.sku == new.sku },
    contentsSame = { old, new -> old.sku == new.sku }
) {
    override val itemLayout: Int
        get() = R.layout.cart_item

    @SuppressLint("SetTextI18n")
    override fun bindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val product = getItem(position)
        holder.itemView.apply {
            val ivCartProduct by lazy { findViewById<ImageView>(R.id.ivCartProduct) }
            val tvCartTitle by lazy { findViewById<TextView>(R.id.tvCartTitle) }
            val tvCartQuantity by lazy { findViewById<TextView>(R.id.tvCartQuantity) }
            val tvPrice by lazy { findViewById<TextView>(R.id.tvCartPrice) }
            val tvCartSubTotal by lazy { findViewById<TextView>(R.id.tvCartSubTotal) }
            val btnRemoveCart by lazy { findViewById<ImageButton>(R.id.btnRemoveCart) }
            val (productPrice, currency) = product.productPrice.split(" ")
            val quantity = cartViewModel.cartListLiveData.value!![product]
            val quantityStringRes = resources.getString(R.string.quantity)
            with(product) {
                Glide.with(context)
                    .load(imageUris[0])
                    .into(ivCartProduct)
                tvCartTitle.text = nameShort
                tvCartQuantity.text = "$quantityStringRes $quantity"
                tvPrice.text = product.productPrice
                tvCartSubTotal.text =
                    "${"%.2f".format(quantity?.times(productPrice.toFloat()))} $currency"
            }

            ivCartProduct.setOnClickListener {
                it.findNavController()
                    .navigateUriWithDefaultOptions(Uri.parse("${DeepLinks.PDP}/${product.sku}"))
            }

            btnRemoveCart.setOnClickListener {
                it.animate {
                    cartViewModel.post(Action(CartOperation.Remove, product))
                }
            }
        }
    }
}