package com.sunragav.catalog.views


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sunragav.catalog.R
import com.sunragav.catalog.views.adpaters.MainCategoriesAdapter
import kotlinx.android.synthetic.main.fragment_catalog.view.*

/**
 * A simple [Fragment] subclass.
 */
class CatalogFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_catalog, container, false)
        view.rvCatalog.adapter = MainCategoriesAdapter()
        return view
    }

}
