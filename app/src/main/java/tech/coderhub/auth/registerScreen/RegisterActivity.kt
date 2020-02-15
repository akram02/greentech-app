package tech.coderhub.auth.registerScreen

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import tech.coderhub.greenapp.R

import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.progressbar.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk25.coroutines.onClick
import tech.coderhub.auth.loginScreen.Token
import tech.coderhub.auth.loginScreen.User
import tech.coderhub.auth.loginScreen.UserSubmit
import tech.coderhub.base.BaseActivity
import tech.coderhub.basic.helper.Constants
import tech.coderhub.greenapp.ui.main.MainActivity

class RegisterActivity : BaseActivity() {
    override fun layoutRes() = R.layout.activity_register
    private val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(
        RegisterViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseViewModel.value = viewModel
        init()
        observeViewModel()
    }

    private fun init() {
        progressBar.bringToFront()
        signUp.onClick {
            val phone = "01" + phone.text.toString()
            val user = UserSubmit(phone, phone, password.text.toString())
            user.firstName = firstName.text.toString()
            user.lastName = lastName.text.toString()
            viewModel.getUserToken(user)
        }
    }


    private fun getUserDetails(token: Token) {
        cache.putValue(Constants.ACCESS_TOKEN, token.token)
        viewModel.getUserDetails()
    }

    private fun observeViewModel() {
        viewModel.tokenLiveData.observe(this, Observer<Token> {
            getUserDetails(it)
        })
        viewModel.userLiveData.observe(this, Observer<User> {
            saveUserDetails(it)
        })
    }

    private fun saveUserDetails(user: User) {
        with(cache) {
            putValue(Constants.ID, user.id)
            putValue(Constants.USERNAME, user.username)
            putValue(Constants.LOGIN, user.login)
            putValue(Constants.FIRST_NAME, user.firstName)
            putValue(Constants.LAST_NAME, user.lastName)
            putValue(Constants.EMAIL, user.email)
        }
        startActivity(intentFor<MainActivity>().clearTop())
        finish()
    }

}
