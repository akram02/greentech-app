package tech.coderhub.auth.changePasswordScreen

import androidx.lifecycle.MutableLiveData
import tech.coderhub.auth.loginScreen.Token
import tech.coderhub.greenapp.di.network.RestApi
import tech.coderhub.base.BaseViewModel
import javax.inject.Inject

class ChangePasswordViewModel @Inject
constructor(private val restApi: RestApi) : BaseViewModel() {
    val tokenLiveData = MutableLiveData<Token>()
    val changedPassword = MutableLiveData<Boolean>()
    fun updatePassword(updatePassword: UpdatePassword) {
        getCompletableResponse(restApi::updatePassword, updatePassword) {
//            tokenLiveData.value = it
            tokenLiveData.value = null
            changedPassword.value = true
        }
    }
}