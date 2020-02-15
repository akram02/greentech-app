package tech.coderhub.greenapp.ui.main

import androidx.lifecycle.MutableLiveData
import tech.coderhub.greenapp.di.network.RestApi
import tech.coderhub.greenapp.model.Programme
import tech.coderhub.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject
constructor(private val restApi: RestApi): BaseViewModel() {
    val allProgrammeLiveData = MutableLiveData<List<Programme>>()

    /*fun getAllProgramme() {
        getResponse(restApi::getAllProgramme) {
            allProgrammeLiveData.value = it
        }
    }*/
}