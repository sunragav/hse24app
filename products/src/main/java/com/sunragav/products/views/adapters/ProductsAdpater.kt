package com.sunragav.products.views.adapters

import android.net.Uri
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sunragav.android_core.adapters.BasePagedListAdapter
import com.sunragav.android_core.animations.animate
import com.sunragav.android_core.deeplinks.DeepLinks
import com.sunragav.android_core.deeplinks.navigateUriWithDefaultOptions
import com.sunragav.domain.models.DomainProduct
import com.sunragav.products.R

class ProductsAdpater : BasePagedListAdapter<DomainProduct>(
    itemsSame = { old, new -> old.sku == new.sku }
) {
    override val itemLayout: Int = R.layout.list_item_with_image

    override fun bindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val product = getItem(position)
        if (product != null) {
            holder.itemView.apply {
                val ivProduct by lazy { findViewById<ImageView>(R.id.ivItem) }
                val tvName by lazy { findViewById<TextView>(R.id.tvName) }
                val tvBrandName by lazy { findViewById<TextView>(R.id.tvBrandName) }
                val tvPrice by lazy { findViewById<TextView>(R.id.tvPrice) }
                val ratings by lazy { findViewById<RatingBar>(R.id.ratingBar) }
                val btnAddtoCart by lazy { findViewById<ImageButton>(R.id.btnAddtoCart) }
                with(product) {
                    Glide.with(context)
                        .load(imageUris[0])
                        .into(ivProduct)
                    tvName.text = nameShort
                    tvBrandName.text = brandNameLong
                    tvPrice.text = productPrice
                    ratings.rating = averageStars.toFloat()
                }
                setOnClickListener {
                    it.findNavController()
                        .navigateUriWithDefaultOptions(Uri.parse("${DeepLinks.PRODUCTS}/${product.sku}"))
                }
                btnAddtoCart.setOnClickListener {
                    it.animate {

                    }
                }
            }
        }
    }
}