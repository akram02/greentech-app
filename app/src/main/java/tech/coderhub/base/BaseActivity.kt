package tech.coderhub.base

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import org.jetbrains.anko.toast
import tech.coderhub.basic.helper.CacheHelper
import java.lang.ref.WeakReference
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var cache: CacheHelper
    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var applicationContext: Application
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var activityContext: Activity
    protected lateinit var activityWeakReferenceContext: WeakReference<Activity>

    val baseViewModel = MutableLiveData<BaseViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(layoutRes())
        activityContext = this
        activityWeakReferenceContext = WeakReference(this)
        baseViewModel.observe(this, Observer<BaseViewModel>{ vm ->
            vm.isLoading.observe(this, Observer<Boolean> { showIndicator(it) })
            vm.networkError.observe(this, Observer<String>{ error -> toast(error)})
            vm.serverResponseError.observe(this, Observer<String>{ error -> toast(error)})
        })
    }

    @LayoutRes
    protected abstract fun layoutRes(): Int

    fun addFragment(fragmentId: Int, fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(fragmentId, fragment)
        transaction.commit()
    }


    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return fragmentInjector
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            onBackPressed()
        return super.onOptionsItemSelected(item)
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
