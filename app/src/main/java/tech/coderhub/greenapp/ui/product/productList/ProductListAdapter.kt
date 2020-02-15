package tech.coderhub.greenapp.ui.product.productList

import android.annotation.SuppressLint
import android.view.View
import kotlinx.android.synthetic.main.item_product.view.*
import tech.coderhub.basic.base.BaseAdapter
import tech.coderhub.greenapp.R
import javax.inject.Inject

class ProductListAdapter @Inject
constructor() : BaseAdapter(R.layout.item_product){
    @SuppressLint("SetTextI18n")
    override fun bindView(view: View, any: Any, position: Int) {
        with(view) {
            val product = any as Product
            name.text = product.userName
            categoryName.text = product.category
            productName.text = product.name
            select.setOnClickListener { clickListener.onItemClick(this, product.id) }
        }
    }
}