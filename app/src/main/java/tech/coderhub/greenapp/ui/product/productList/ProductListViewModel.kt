package tech.coderhub.greenapp.ui.product.productList

import androidx.lifecycle.MutableLiveData
import tech.coderhub.base.BaseViewModel
import tech.coderhub.greenapp.di.network.RestApi
import javax.inject.Inject

class ProductListViewModel @Inject
constructor(private val restApi: RestApi): BaseViewModel() {
    val productListLiveData = MutableLiveData<List<Product>>()
    fun getAllProduct() {
        getResponse(restApi::getAllProduct){
            productListLiveData.value = it
        }
    }
}