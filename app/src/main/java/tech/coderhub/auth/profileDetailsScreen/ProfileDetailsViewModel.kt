package tech.coderhub.auth.profileDetailsScreen

import androidx.lifecycle.MutableLiveData
import tech.coderhub.greenapp.di.network.RestApi
import tech.coderhub.base.BaseViewModel
import javax.inject.Inject

class ProfileDetailsViewModel @Inject
constructor(private val restApi: RestApi) : BaseViewModel() {
    val profileLiveData = MutableLiveData<Profile>()
    fun getProfile() {
        getResponse(restApi::getProfile) {
            profileLiveData.value = it
        }
    }
}