package com.sunragav.products.views


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.sunragav.presentation.ProductsViewModel
import com.sunragav.products.R
import com.sunragav.products.views.adapters.ProductsAdpater
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_products.view.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class ProductsFragment : Fragment() {
    @Inject
    lateinit var factory: ProductsViewModel.Factory

    private val args: ProductsFragmentArgs by navArgs()

    val viewModel: ProductsViewModel by viewModels(factoryProducer = { factory })

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_products, container, false)
        val adapter = ProductsAdpater()

        view.rvProducts.also { rv ->
            rv.layoutManager = GridLayoutManager(context, 1)

            rv.adapter = adapter
        }

        viewModel.productsByCategoryLiveData.postValue(args.catalogId.toInt())

        viewModel.productsLiveData.observe(this, Observer {
            adapter.submitList(it)
        })

        return view
    }
}


