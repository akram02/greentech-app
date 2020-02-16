package tech.coderhub.greenapp.ui.product.productView

import androidx.lifecycle.MutableLiveData
import tech.coderhub.base.BaseViewModel
import tech.coderhub.greenapp.di.network.RestApi
import tech.coderhub.greenapp.ui.product.productList.Product
import javax.inject.Inject

class ProductViewViewModel @Inject
constructor(private val restApi: RestApi): BaseViewModel() {
    val productLiveData = MutableLiveData<Product>()
    val isSubmitted = MutableLiveData<Boolean>()
    fun getProduct(id: String) {
        getResponse(restApi::getProduct, id) {
            productLiveData.value = it
        }
    }

    fun submitOrder(orderVM: OrderVm) {
        getResponse(restApi::submitOrder, orderVM) {
            isSubmitted.value = true
        }
    }
}
