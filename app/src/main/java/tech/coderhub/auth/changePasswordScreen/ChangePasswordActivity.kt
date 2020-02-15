package tech.coderhub.auth.changePasswordScreen

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_change_password.*
import kotlinx.android.synthetic.main.app_bar_layout.*
import kotlinx.android.synthetic.main.progressbar.*
import org.jetbrains.anko.toast
import tech.coderhub.auth.loginScreen.Token
import tech.coderhub.greenapp.R
import tech.coderhub.greenapp.databinding.ActivityChangePasswordBinding
import tech.coderhub.base.BaseActivity
import tech.coderhub.base.showIndicator
import tech.coderhub.basic.helper.Constants

class ChangePasswordActivity : BaseActivity() {

    private val changePasswordViewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(
        ChangePasswordViewModel::class.java) }

    override fun layoutRes() = R.layout.activity_change_password

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityChangePasswordBinding = DataBindingUtil.setContentView(this, R.layout.activity_change_password)
        binding.lifecycleOwner = this
        binding.viewModel = changePasswordViewModel
        observeViewModel()
        initField()
        progressBar.bringToFront()
    }

    private fun initField() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Password change activity"
            setDisplayHomeAsUpEnabled(true)
        }

        /*newPassword.doAfterTextChanged {
            passwordLayout.error = null
        }*/

        submitPassword.setOnClickListener {
            if (newPassword.length()<6) {
//                passwordLayout.error = "Password or confirm newPassword is invalid"
                toast("Password or confirm newPassword is invalid")
                return@setOnClickListener
            }
            changePasswordViewModel.updatePassword(UpdatePassword(currentPassword.text.toString(), newPassword.text.toString()))
        }
    }

    private fun observeViewModel() {
        changePasswordViewModel.isLoading.observe(this, Observer<Boolean> { showIndicator(it) })
        changePasswordViewModel.networkError.observe(this, Observer<String> { errorMessage -> toast(errorMessage) })
        changePasswordViewModel.serverResponseError.observe(this, Observer<String> { errorMessage -> toast(errorMessage) })

        changePasswordViewModel.tokenLiveData.observe(this, Observer<Token> {
//            cache.putValue(Constants.ACCESS_TOKEN, it.token)
            super.onBackPressed()
        })
    }
}
