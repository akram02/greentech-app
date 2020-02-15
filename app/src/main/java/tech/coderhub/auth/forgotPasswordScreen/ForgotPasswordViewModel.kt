package tech.coderhub.auth.forgotPasswordScreen

import androidx.lifecycle.MutableLiveData
import tech.coderhub.auth.loginScreen.User
import tech.coderhub.greenapp.di.network.RestApi
import tech.coderhub.base.BaseViewModel
import javax.inject.Inject

class ForgotPasswordViewModel @Inject
constructor(private val restApi: RestApi) : BaseViewModel() {
    val forgetPasswordLiveData = MutableLiveData<ForgotPassword>()
    val userLiveData = MutableLiveData<User>()
    val tokenLiveData = MutableLiveData<String>()

    fun sendPinToEmail(forgotPassword: ForgotPassword) {
        getResponse(restApi::sendPinToEmail, forgotPassword) {
            forgetPasswordLiveData.value = it
        }
    }

    fun changePassword(passwordSubmit: PasswordSubmit) {
        getResponse(restApi::changePassword, passwordSubmit) {
            userLiveData.value = it.user
            tokenLiveData.value = it.token
        }
    }
}