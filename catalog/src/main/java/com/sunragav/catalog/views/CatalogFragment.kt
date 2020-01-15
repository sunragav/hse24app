package com.sunragav.catalog.views


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.sunragav.catalog.R
import com.sunragav.catalog.models.DomainUIModelMapper
import com.sunragav.catalog.views.adpaters.MainCategoriesAdapter
import com.sunragav.presentation.CatalogViewModel
import com.sunragav.presentation.UiState
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_catalog.view.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class CatalogFragment : Fragment() {
    @Inject
    lateinit var catalogViewModelFactory: CatalogViewModel.Factory
    private val catalogViewModel by viewModels<CatalogViewModel>(factoryProducer = { catalogViewModelFactory })


    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_catalog, container, false)
        val adapter = MainCategoriesAdapter()
        val domainUIModelMapper = DomainUIModelMapper()
        view.rvCatagories.apply {
            this.adapter = adapter
            layoutManager = GridLayoutManager(activity, 1)
        }
        catalogViewModel.catalogLiveData.observe(this, Observer { catalogList ->
            val catalogUi = catalogList.map { domainUIModelMapper.toUi(it) }
            adapter.submitList(catalogUi)
        })
        catalogViewModel.uiState.observe(this, Observer {
            view.rvCatagories.visibility = if (it == UiState.Complete) View.VISIBLE else View.GONE
            view.vLoading.visibility = if (it == UiState.Loading) View.VISIBLE else View.GONE
            view.vEmpty.visibility =
                if (it is UiState.Error || it == UiState.Empty) View.VISIBLE else View.GONE
        })
        return view
    }

}
