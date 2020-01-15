package com.sunragav.pdp.views


import android.content.Context
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.sunragav.android_core.animations.animate
import com.sunragav.pdp.R
import com.sunragav.presentation.PDPViewModel
import com.sunragav.presentation.UiState
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_pdp.view.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class PDPFragment : Fragment() {
    @Inject
    lateinit var factory: PDPViewModel.Factory

    private val args: PDPFragmentArgs by navArgs()

    val viewModel: PDPViewModel by viewModels(factoryProducer = { factory })

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pdp, container, false)
        val lifecycleOwner = this
        viewModel.pdpBySkuLiveData.postValue(args.sku)
        view.apply {
            val ivProduct by lazy { findViewById<ImageView>(R.id.ivItem) }
            val tvName by lazy { findViewById<TextView>(R.id.tvName) }
            val tvBrandName by lazy { findViewById<TextView>(R.id.tvBrandName) }
            val tvPrice by lazy { findViewById<TextView>(R.id.tvPrice) }
            val ratings by lazy { findViewById<RatingBar>(R.id.ratingBar) }
            val btnAddtoCart by lazy { findViewById<ImageButton>(R.id.btnAddtoCart) }

            viewModel.pdpLiveData.observe(lifecycleOwner, Observer { product ->
                with(product) {
                    com.bumptech.glide.Glide.with(context)
                        .load(imageUris[0])
                        .into(ivProduct)
                    tvName.text = nameShort
                    tvBrandName.text = brandNameLong
                    tvPrice.text = productPrice
                    ratings.rating = averageStars.toFloat()
                    tvDescription.text =
                        Html.fromHtml(longDescription, Html.FROM_HTML_MODE_COMPACT)

                }

                btnAddtoCart.setOnClickListener {
                    it.animate {

                    }
                }
            })

            viewModel.uiState.observe(lifecycleOwner, Observer {
                with(view) {
                    mcvPDP.visibility = if (it == UiState.Complete) View.VISIBLE else View.GONE
                    vLoading.visibility = if (it == UiState.Loading) View.VISIBLE else View.GONE
                    vEmpty.visibility =
                        if (it is UiState.Error || it == UiState.Empty) View.VISIBLE else View.GONE
                }
            })
        }


        return view
    }


}
