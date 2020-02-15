package tech.coderhub.auth.profileEditScreen

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import tech.coderhub.auth.changePasswordScreen.ChangePasswordActivity
import tech.coderhub.auth.profileDetailsScreen.Profile

import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profile_edit.*
import kotlinx.android.synthetic.main.app_bar_layout.*
import kotlinx.android.synthetic.main.progressbar.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import tech.coderhub.greenapp.R
import tech.coderhub.greenapp.databinding.ActivityProfileEditBinding
import tech.coderhub.greenapp.helper.uploadImageToView
import tech.coderhub.base.BaseActivity
import tech.coderhub.base.showIndicator
import tech.coderhub.basic.helper.Constants
import tech.coderhub.basic.helper.byteArrayToStringImage
import tech.coderhub.basic.helper.getFileDataFromDrawable
import tech.coderhub.basic.helper.scaleTo1920Max

class ProfileEditActivity : BaseActivity() {

    private val profileEditViewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(
        ProfileEditViewModel::class.java) }

    override fun layoutRes() = R.layout.activity_profile_edit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressBar.bringToFront()
        val binding: ActivityProfileEditBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile_edit)
        binding.lifecycleOwner = this
        binding.viewModel = profileEditViewModel
        initField()
        observeViewModel()
        profileEditViewModel.getProfile()
        passwordChange.setOnClickListener {
            startActivity<ChangePasswordActivity>()
        }
    }

    private fun observeViewModel() {
        profileEditViewModel.let {
            it.isLoading.observe(this, Observer<Boolean> { showIndicator(it) })
            it.networkError.observe(this, Observer<String> { error -> toast(error) })
            it.serverResponseError.observe(this, Observer<String> { error -> toast(error) })
            it.profileLiveData.observe(this, Observer<Profile> {
                showProfile(it)
            })
            it.imageUploadStatus.observe(this, Observer<String> {
                if (it == "success") {
                    toast("Profile updated successfully")
                    super.onBackPressed()
                }
                else {
                    toast("Something went wrong")
                }
            })
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showProfile(it: Profile) {
        if(it.imageUrl!="") Picasso.get().load(it.imageUrl).memoryPolicy(MemoryPolicy.NO_CACHE).into(profileImage)
        profileImage.setOnClickListener {
            uploadImageToView()
        }
        updateButton.setOnClickListener {
            val imageByte = getFileDataFromDrawable(profileImage.drawable)
            profileEditViewModel.updateUser(UserDTO(byteArrayToStringImage(imageByte), fullName.text.toString()))
        }
    }

    private fun initField() {
        setSupportActionBar(toolbar)
        with(supportActionBar!!) {
            title = "Edit User Information"
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            Constants.TAKE_PICTURE_CODE -> {
                data?.extras?.let {
                    profileImage!!.setImageBitmap(it.get("data") as Bitmap)
                    profileImage!!.setImageBitmap(scaleTo1920Max(profileImage.drawable))
                }
            }
            Constants.PICK_PHOTO_CODE -> {
                data?.let {
                    profileImage!!.setImageURI(it.data)
                    profileImage!!.setImageBitmap(scaleTo1920Max(profileImage.drawable))
                }
            }
        }
    }
}
