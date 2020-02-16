package tech.coderhub.greenapp.ui.product.productView

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_product_view.*
import tech.coderhub.greenapp.R

import kotlinx.android.synthetic.main.app_bar_layout.*
import kotlinx.android.synthetic.main.progressbar.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import tech.coderhub.base.BaseActivity
import tech.coderhub.greenapp.ui.product.productList.Product

class ProductViewActivity  : BaseActivity() {
    val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(ProductViewViewModel::class.java) }
    var product = Product()
    override fun layoutRes() = R.layout.activity_product_view
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initField()
        observeViewModel()
        viewModel.getProduct(intent.getStringExtra("id")!!)
    }

    private fun initField() {
        progressBar.bringToFront()
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        baseViewModel.value = viewModel
        submit.onClick {
            val orderVm = OrderVm()
            orderVm.productId = product.id
            orderVm.description = description.text.toString()
            viewModel.submitOrder(orderVm)
        }
    }

    private fun observeViewModel() {
        viewModel.productLiveData.observe(this, Observer<Product>{
            this.product = it
            populateData(it)
        })
        viewModel.isSubmitted.observe(this, Observer {
            descriptionLayout.visibility = View.GONE
            successFulSubmit.visibility = View.VISIBLE
            submit.visibility = View.GONE
        })
    }

    private fun populateData(product: Product) {
        name.text = product.userName
        categoryName.text = product.category + ": "
        productName.text = product.name
        rating.text = "4.5"
    }
}
