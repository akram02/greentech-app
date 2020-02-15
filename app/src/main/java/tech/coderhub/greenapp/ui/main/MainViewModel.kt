package tech.coderhub.greenapp.ui.main

import androidx.lifecycle.MutableLiveData
import tech.coderhub.greenapp.di.network.RestApi
import tech.coderhub.base.BaseViewModel
import tech.coderhub.greenapp.ui.product.productList.Product
import javax.inject.Inject

class MainViewModel @Inject
constructor(private val restApi: RestApi): BaseViewModel() {
    val allProgrammeLiveData = MutableLiveData<List<Product>>()

    /*fun getAllProgramme() {
        getResponse(restApi::getAllProgramme) {
            allProgrammeLiveData.value = it
        }
    }*/
}