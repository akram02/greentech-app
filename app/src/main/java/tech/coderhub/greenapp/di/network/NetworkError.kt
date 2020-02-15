package tech.coderhub.greenapp.di.network

import org.jetbrains.anko.clearTask
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import retrofit2.HttpException
import tech.coderhub.auth.loginScreen.LoginActivity
import tech.coderhub.greenapp.MyApplication
import tech.coderhub.greenapp.R
import tech.coderhub.basic.helper.CacheHelper
import java.io.IOException
import java.net.SocketTimeoutException

object NetworkError {
    fun getServerResponseMessage(throwable: Throwable): String {
        if (throwable is SocketTimeoutException) {
            return MyApplication.getInstance().resources.getString(R.string.time_out_error)
        } else if (throwable is IOException) {
            return MyApplication.getInstance().resources
                .getString(R.string.oops_connect_your_internet)
        } else if (getErrorCode(throwable) == 401) {
            val myApplication = MyApplication.getInstance()
            val cacheHelper = CacheHelper(myApplication)
            cacheHelper.clearAllCache()
            val context = myApplication.baseContext
            context.startActivity(context.intentFor<LoginActivity>().newTask().clearTop().clearTask())
            return "Please Login again"
        }
        return MyApplication.getInstance().resources.getString(R.string.unknown_error)
    }

    fun getErrorCode(throwable: Throwable): Int {
        return (throwable as HttpException).code()
    }
}