package tech.coderhub.auth.profileDetailsScreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import tech.coderhub.auth.profileEditScreen.ProfileEditActivity
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profile_details.*
import kotlinx.android.synthetic.main.app_bar_layout.*
import kotlinx.android.synthetic.main.progressbar.*
import org.jetbrains.anko.toast
import tech.coderhub.greenapp.R
import tech.coderhub.greenapp.databinding.ActivityProfileDetailsBinding
import tech.coderhub.base.BaseActivity
import tech.coderhub.base.showIndicator
import tech.coderhub.basic.helper.Constants

class ProfileDetailsActivity : BaseActivity() {
    val profileDetailsViewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(
        ProfileDetailsViewModel::class.java) }
    override fun layoutRes() = R.layout.activity_profile_details
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityProfileDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile_details)
//        binding.lifecycleOwner = this
        binding.viewModel = profileDetailsViewModel
        progressBar.bringToFront()
        initField()
        observeViewModel()
        profileDetailsViewModel.getProfile()
    }

    private fun observeViewModel() {
        profileDetailsViewModel.let {
            it.isLoading.observe(this, Observer<Boolean> { showIndicator(it) })
            it.networkError.observe(this, Observer<String> { error -> toast(error) })
            it.serverResponseError.observe(this, Observer<String> { error -> toast(error) })
            it.profileLiveData.observe(this, Observer<Profile> {
                showProfile(it)
            })
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showProfile(it: Profile) {
        if(it.imageUrl!="") Picasso.get().load(it.imageUrl).memoryPolicy(MemoryPolicy.NO_CACHE).into(profilePhoto)
        updateButton.setOnClickListener {
            startActivityForResult(Intent(activityContext, ProfileEditActivity::class.java), Constants.PROFILE_EDIT_REQUEST_CODE)
        }
    }

    private fun initField() {
        setSupportActionBar(toolbar)
        with(supportActionBar!!) {
            title = "User Information"
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==Constants.PROFILE_EDIT_REQUEST_CODE) {
            profileDetailsViewModel.getProfile()
        }
    }
}
