package tech.coderhub.greenapp.ui.main

import android.os.Bundle
import android.view.View
import android.widget.PopupWindow
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.beardedhen.androidbootstrap.TypefaceProvider
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_layout.*
import kotlinx.android.synthetic.main.nav_main.view.*
import kotlinx.android.synthetic.main.progressbar.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk25.coroutines.onClick
import tech.coderhub.auth.loginScreen.LoginActivity
import tech.coderhub.base.BaseActivity
import tech.coderhub.basic.helper.Constants
import tech.coderhub.basic.helper.Constants.FIRST_NAME
import tech.coderhub.basic.helper.Constants.LAST_NAME
import tech.coderhub.basic.helper.Constants.LOGIN
import tech.coderhub.greenapp.R
import tech.coderhub.greenapp.model.Programme


class MainActivity : BaseActivity(){
    private val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java) }
    private var popupWindow: PopupWindow? = null
    private var allProgramme = arrayListOf<String>()
    override fun layoutRes() = R.layout.activity_main
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Available Services"
            setDisplayHomeAsUpEnabled(true)
        }
        progressBar.bringToFront()
        baseViewModel.value = viewModel
        observeViewModel()
        TypefaceProvider.registerDefaultIconSets()

        val navigationView = navigationView
        val navHeader = navigationView.getHeaderView(0)
        onNavigationMenuClick(navHeader)
        navHeader.navMain.onClick {
            closeDrawer()
        }
    }

    private fun closeDrawer() {
        drawer.closeDrawer(GravityCompat.START)
    }

    private fun observeViewModel() {
        viewModel.allProgrammeLiveData.observe(this, Observer<List<Programme>> {
            allProgramme.clear()
            viewModel.allProgrammeLiveData.value?.forEach {
                allProgramme.add(it.name)
            }
        })
    }

    private fun onNavigationMenuClick(navHeader: View) {
        navHeader.apply {
            logout.onClick {
                cache.putValue(Constants.ACCESS_TOKEN, "")
                startActivity(intentFor<LoginActivity>().clearTop())
                closeDrawer()
                finish()
            }
            val name = cache.getValue(FIRST_NAME) + cache.getValue(LAST_NAME)
            firstNameLastName.text = name
            phone.text = cache.getValue(LOGIN)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        popupWindow?.dismiss()
    }
}
