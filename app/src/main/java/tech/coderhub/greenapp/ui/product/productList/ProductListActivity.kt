package tech.coderhub.greenapp.ui.product.productList

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.beardedhen.androidbootstrap.TypefaceProvider
import tech.coderhub.greenapp.R

import kotlinx.android.synthetic.main.activity_product_list.*
import kotlinx.android.synthetic.main.app_bar_layout.*
import kotlinx.android.synthetic.main.progressbar.*
import org.jetbrains.anko.toast
import tech.coderhub.base.BaseActivity
import tech.coderhub.basic.listener.ClickListener
import javax.inject.Inject

class ProductListActivity : BaseActivity(), ClickListener {
    @Inject
    lateinit var adapter: ProductListAdapter
    val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(ProductListViewModel::class.java) }
    override fun layoutRes() = R.layout.activity_product_list
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initField()
        observeViewModel()
    }

    private fun initField() {
        baseViewModel.value = viewModel
        TypefaceProvider.registerDefaultIconSets()
        progressBar.bringToFront()
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        recyclerView.adapter = adapter
        adapter.setItemClickListener(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        viewModel.getAllProduct()
    }

    private fun observeViewModel() {
        viewModel.productListLiveData.observe(this, Observer<List<Product>> {
            adapter.setItemList(it.toMutableList())
            if (it.isEmpty()) {
                toast("No Product Found")
            }
        })
    }

    override fun onItemClick(item: View, any: Any) {

    }

}
