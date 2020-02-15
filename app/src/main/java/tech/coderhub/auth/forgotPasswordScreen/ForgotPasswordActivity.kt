package tech.coderhub.auth.forgotPasswordScreen

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.app_bar_layout.*
import kotlinx.android.synthetic.main.progressbar.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import tech.coderhub.auth.loginScreen.User

import tech.coderhub.greenapp.R
import tech.coderhub.greenapp.ui.main.MainActivity
import tech.coderhub.base.BaseActivity
import tech.coderhub.base.showIndicator
import tech.coderhub.basic.helper.Constants
import tech.coderhub.basic.helper.getDeviceId

class ForgotPasswordActivity : BaseActivity() {
    private val forgotPasswordViewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(
        ForgotPasswordViewModel::class.java) }

    override fun layoutRes() = R.layout.activity_forgot_password

    private var forgotId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressBar.bringToFront()
        observeViewModel()
        initField()
    }

    private fun initField() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "PasswordSubmit Recovery"
            setDisplayHomeAsUpEnabled(true)
        }
        pin.visibility = View.GONE
        arrayOf(pinLayout, passwordLayout, confirmPasswordLayout, submitPassword).forEach {
            it.visibility = View.GONE
        }
        submitPhone.setOnClickListener {
            forgotPasswordViewModel.sendPinToEmail(ForgotPassword(phone.text.toString(), getDeviceId()))
        }

        submitPassword.setOnClickListener {
            forgotPasswordViewModel.changePassword(PasswordSubmit(password.text.toString(), pin.text.toString(), forgotId))
        }
    }

    private fun observeViewModel() {
        forgotPasswordViewModel.isLoading.observe(this, Observer<Boolean> { showIndicator(it) })
        forgotPasswordViewModel.networkError.observe(this, Observer<String> { errorMessage -> toast(errorMessage) })
        forgotPasswordViewModel.serverResponseError.observe(this, Observer<String> { errorMessage -> toast(errorMessage) })
        forgotPasswordViewModel.forgetPasswordLiveData.observe(this, Observer<ForgotPassword> {
            if (it.id == -1) toast("User Is Not registered")
            else {
                forgotId = it.id
                phoneLayout.visibility = View.GONE
                submitPhone.visibility = View.GONE
                pinLayout.visibility = View.VISIBLE
                passwordLayout.visibility = View.VISIBLE
                confirmPasswordLayout.visibility = View.VISIBLE
                submitPassword.visibility = View.VISIBLE
            }
        })
        forgotPasswordViewModel.userLiveData.observe(this, Observer<User> {
            saveUserData(it)
        })
        forgotPasswordViewModel.tokenLiveData.observe(this, Observer<String> {
            cache.putValue(Constants.ACCESS_TOKEN, it)
            startActivity(intentFor<MainActivity>().clearTop())
        })
    }

    private fun saveUserData(it: User) {
        with(cache) {
            putValue(Constants.ID, it.id)
            putValue(Constants.USERNAME, it.username)
            putValue(Constants.OBJECT_ID, it.objectId)
            putValue(Constants.USER_TYPE, it.userType)
            putValue(Constants.STATUS_ID, it.statusId)
            putValue(Constants.SUPERVISOR_ID, it.supervisorId)
            putValue(Constants.EMAIL, it.email)
            putValue(Constants.EMAIL_VERIFIED_AT, it.emailVerifiedAt)
            putValue(Constants.STATUS, it.status)
        }
    }
}
