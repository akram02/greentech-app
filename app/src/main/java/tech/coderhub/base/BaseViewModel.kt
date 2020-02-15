package tech.coderhub.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tech.coderhub.greenapp.di.network.NetworkError
import timber.log.Timber

open class BaseViewModel : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val networkError = MutableLiveData<String>()
    val serverResponseError = MutableLiveData<String>()

    fun <T : Any, U : Any> getResponse(
        requesterMethod: suspend(T) -> U,
        body: T,
        onResponseMethod: (response: U) -> Unit
    ) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                onResponseMethod(requesterMethod(body))
            }
            catch (it: Throwable) {
                networkError.value = NetworkError.getServerResponseMessage(it)
                Timber.e(it.localizedMessage)
            }
            isLoading.value = false
        }
    }

    fun <U : Any> getSameResponse(
        requesterMethod: suspend(U) -> U,
        body: U,
        onResponseMethod: (response: U) -> Unit
    ) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                onResponseMethod(requesterMethod(body))
            }
            catch (it: Throwable) {
                networkError.value = NetworkError.getServerResponseMessage(it)
                Timber.e(it.localizedMessage)
            }
            isLoading.value = false
        }
    }

    fun <U : Any> getResponse(
        requesterMethod: suspend() -> U,
        onResponseMethod: (response: U) -> Unit
    ) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                onResponseMethod(requesterMethod())
            }
            catch (it: Throwable) {
                networkError.value = NetworkError.getServerResponseMessage(it)
                Timber.e(it.localizedMessage)
            }
            isLoading.value = false
        }
    }

    fun <U : Any> getCompletableResponse(
        requesterMethod: suspend(U) -> Unit,
        body: U,
        onResponseMethod: () -> Unit
    ) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                requesterMethod(body)
                onResponseMethod()
            }
            catch (it: Throwable) {
                networkError.value = NetworkError.getServerResponseMessage(it)
                Timber.e(it.localizedMessage)
            }
            isLoading.value = false
        }
    }
}