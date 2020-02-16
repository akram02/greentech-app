package tech.coderhub.greenapp.ui.main

import android.os.Bundle
import android.view.MenuItem
import android.widget.PopupWindow
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_layout.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_main.view.*
import kotlinx.android.synthetic.main.progressbar.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import tech.coderhub.auth.loginScreen.LoginActivity
import tech.coderhub.base.BaseActivity
import tech.coderhub.basic.helper.Constants.FIRST_NAME
import tech.coderhub.basic.helper.Constants.LAST_NAME
import tech.coderhub.basic.helper.Constants.LOGIN
import tech.coderhub.greenapp.R
import tech.coderhub.greenapp.ui.product.productList.ProductListActivity
import tech.coderhub.greenapp.ui.product.productView.ProductViewActivity


class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener{
    private val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java) }
    private var popupWindow: PopupWindow? = null
    private var allProgramme = arrayListOf<String>()
    override fun layoutRes() = R.layout.activity_main
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        val navigationView = navigationView
        val navHeader = navigationView.getHeaderView(0)
        navHeader.apply {
            val name = cache.getValue(FIRST_NAME) + cache.getValue(LAST_NAME)
            firstNameLastName.text = name
            phone.text = cache.getValue(LOGIN)
        }
        navigationView.setNavigationItemSelectedListener(this)
/*        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Available Services"
//            setDisplayHomeAsUpEnabled(true)
        }*/
        progressBar.bringToFront()
        baseViewModel.value = viewModel
        homeMenuClickListener()
    }

    private fun homeMenuClickListener() {
        freshFood.onClick { goToList("Food") }
        parlor.onClick { goToList("Parlor") }
        dresses.onClick { goToList("Dress") }
        makeup.onClick { goToList("Makeup") }
        handyCraft.onClick { goToList("Handy Craft") }
        tutor.onClick { goToList("Tutor") }
    }

    private fun goToList(category: String) {
        startActivity<ProductListActivity>("category" to category)
    }

    private fun closeDrawer() {
        drawer.closeDrawer(GravityCompat.START)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> { }
            R.id.logout -> {
                cache.clearAllCache()
                startActivity(intentFor<LoginActivity>().clearTop())
                closeDrawer()
                finish()
            }
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        popupWindow?.dismiss()
    }
}
