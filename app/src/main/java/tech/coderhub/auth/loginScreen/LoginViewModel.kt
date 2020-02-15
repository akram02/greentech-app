package tech.coderhub.auth.loginScreen

import androidx.lifecycle.MutableLiveData
import tech.coderhub.greenapp.di.network.RestApi
import tech.coderhub.base.BaseViewModel
import javax.inject.Inject

class LoginViewModel @Inject
constructor(private val restApi: RestApi) : BaseViewModel() {
    val userLiveData = MutableLiveData<User>()
    val tokenLiveData = MutableLiveData<Token>()
    val invalidAuth = MutableLiveData<Boolean>()
    fun getUserDetails() {
        getResponse(restApi::getUserDetails) {
            if (it.id == null) {
                serverResponseError.value = "Something went wrong"
                if (it.status=="401") {
                    invalidAuth.value = true
                }
            } else {
                userLiveData.value = it
            }
        }
    }

    fun getUserToken(userSubmit: UserSubmit) {
        getResponse(restApi::getUserToken, userSubmit) {
            println(it.error)
            if (it.error != null && it.error == "Unauthorised") {
                invalidAuth.value = true
            }
            else if (it.token == null) {
                serverResponseError.value = "Something went wrong"
            } else {
                tokenLiveData.value = it
            }
        }
    }
}
