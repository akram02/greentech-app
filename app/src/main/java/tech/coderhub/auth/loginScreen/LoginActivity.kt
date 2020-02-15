package tech.coderhub.auth.loginScreen

import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_login_auth.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import tech.coderhub.auth.forgotPasswordScreen.ForgotPasswordActivity
import tech.coderhub.auth.registerScreen.RegisterActivity
import tech.coderhub.greenapp.R
import tech.coderhub.greenapp.ui.main.MainActivity
import tech.coderhub.base.BaseActivity
import tech.coderhub.base.showIndicator
import tech.coderhub.basic.helper.Constants

class LoginActivity : BaseActivity() {
    override fun layoutRes() = R.layout.activity_login_auth
    var isUserLoggedIn = false
    private val loginViewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createChannel(
                getString(R.string.helpline_notification_channel_id),
                getString(R.string.demo_notification)
        )
        init()
        retryLayout.setOnClickListener {
            retryLayout.visibility = View.GONE
            init()
        }
        observeViewModel()
    }

    private fun init() {
        progressBar.bringToFront()
        checkLogin()
        register.onClick {
            startActivity<RegisterActivity>()
        }
        loginButton.setOnClickListener {


            var error = false
            if (phone.text?.length == 0) {
                phoneLayout.error = "User ID is required"
                error = true
            }
            if (password.text?.length == 0) {
                passwordLayout.error = "Password is required"
                error = true
            }
            if (!error)
                loginViewModel.getUserToken(UserSubmit("01" + phone.text.toString(), password.text.toString()))
        }
        phone.doAfterTextChanged {
            phoneLayout.error = null
        }
        password.doAfterTextChanged {
            passwordLayout.error = null
        }
        forgotPassword.setOnClickListener { startActivity<ForgotPasswordActivity>() }
    }

    private fun checkLogin() {
        if (cache.getValue(Constants.ACCESS_TOKEN) != "") {
            loginLayout.visibility = View.GONE
            isUserLoggedIn = true
            loginViewModel.getUserDetails()
        }
        else {
            retryLayout.visibility = View.GONE
        }
    }

    private fun observeViewModel() {
        loginViewModel.isLoading.observe(this, Observer<Boolean> {
            showIndicator(it)
            loginButton.isEnabled = !it
        })
        loginViewModel.networkError.observe(this, Observer<String> { errorMessage ->
            if (isUserLoggedIn) {
                retryLayout.visibility = View.VISIBLE
            }
            toast(errorMessage)
        })
        loginViewModel.serverResponseError.observe(this, Observer<String> { errorMessage ->
            toast(errorMessage)
            loginLayout.visibility = View.VISIBLE
        })
        loginViewModel.tokenLiveData.observe(this, Observer<Token> { getUserDetails(it) })
        loginViewModel.userLiveData.observe(this, Observer<User> { saveUserDetails(it) })
        loginViewModel.invalidAuth.observe(this, Observer<Boolean> {
            toast("Invalid Credentials")
        })
    }

    private fun getUserDetails(token: Token) {
        cache.putValue(Constants.ACCESS_TOKEN, token.token)
        loginViewModel.getUserDetails()
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

    private fun createChannel(channelId: String, channelName: String) {
        // TODO: Step 1.6 START create a channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            val notificationChannel = NotificationChannel(
                    channelId,
                    channelName,
                    // TODO: Step 2.4 change importance
                    NotificationManager.IMPORTANCE_HIGH
            )
                    // TODO: Step 2.6 disable badges for this channel
                    .apply {
                        setShowBadge(false)
                    }

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = "Description"

            val notificationManager = getSystemService(
                    NotificationManager::class.java
            )

            notificationManager.createNotificationChannel(notificationChannel)

        }
        // TODO: Step 1.6 END create channel
    }
}
