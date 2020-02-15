package tech.coderhub.auth.profileEditScreen

import androidx.lifecycle.MutableLiveData
import tech.coderhub.auth.profileDetailsScreen.Profile
import tech.coderhub.greenapp.di.network.RestApi
import tech.coderhub.base.BaseViewModel
import javax.inject.Inject

class ProfileEditViewModel @Inject
constructor(private val restApi: RestApi): BaseViewModel() {
    val imageUploadStatus = MutableLiveData<String>()
    val profileLiveData = MutableLiveData<Profile>()

    fun updateUser(userDTO: UserDTO) {
        getSameResponse(restApi::updateUser, userDTO) {
            imageUploadStatus.value = it.status
        }
    }
    fun getProfile() {
        getResponse(restApi::getProfile) {
            profileLiveData.value = it
        }
    }
}