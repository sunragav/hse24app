package com.sunragav.cart.views


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.sunragav.cart.R
import com.sunragav.cart.adapter.CartAdapter
import com.sunragav.presentation.CartViewModel
import kotlinx.android.synthetic.main.fragment_cart.view.*

/**
 * A simple [Fragment] subclass.
 */
class CartFragment : Fragment() {

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cart, container, false)
        val cartViewModel =
            activity?.let { ViewModelProviders.of(it).get(CartViewModel::class.java) }
        val adapter = CartAdapter(cartViewModel!!)
        view.rvCart.apply {
            layoutManager = GridLayoutManager(context, 1)
            this.adapter = adapter
        }

        cartViewModel.cartListLiveData.observe(this, Observer { cartDomainMap ->
            adapter.submitList(cartDomainMap.map { it.key })
            adapter.notifyDataSetChanged()
        })

        cartViewModel.cartCount.observe(this, Observer {
            view.vEmpty.visibility = if (it == 0) View.VISIBLE else View.GONE
            view.rvCart.visibility = if (it != 0) View.VISIBLE else View.GONE
        })


        val totalAmountStr = resources.getString(R.string.total_amount)
        val currency = resources.getString(R.string.currency)
        cartViewModel.totalAmount.observe(this, Observer {
            view.tvCartTotalAmount.text = "$totalAmountStr ${"%.2f".format(it)} $currency"
        })
        return view
    }


}
