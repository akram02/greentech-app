package tech.coderhub.greenapp.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import tech.coderhub.auth.changePasswordScreen.ChangePasswordViewModel;
import tech.coderhub.auth.forgotPasswordScreen.ForgotPasswordViewModel;
import tech.coderhub.auth.loginScreen.LoginViewModel;
import tech.coderhub.auth.profileDetailsScreen.ProfileDetailsViewModel;
import tech.coderhub.auth.profileEditScreen.ProfileEditViewModel;
import tech.coderhub.auth.registerScreen.RegisterViewModel;
import tech.coderhub.greenapp.ui.main.MainViewModel;
import tech.coderhub.basic.base.BaseViewModelFactory;
import tech.coderhub.basic.di.ViewModelKey;
import tech.coderhub.greenapp.ui.product.productList.ProductListViewModel;


@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindsMainViewModel(MainViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ForgotPasswordViewModel.class)
    abstract ViewModel bindsForgotPasswordViewModel(ForgotPasswordViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ProfileDetailsViewModel.class)
    abstract ViewModel bindsProfileDetailsViewModel(ProfileDetailsViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindsLoginViewModel(LoginViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel.class)
    abstract ViewModel bindsRegisterViewModel(RegisterViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ProfileEditViewModel.class)
    abstract ViewModel bindsProfileEditViewModel(ProfileEditViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ChangePasswordViewModel.class)
    abstract ViewModel bindsChangePasswordViewModel(ChangePasswordViewModel viewModel);



    @Binds
    @IntoMap
    @ViewModelKey(ProductListViewModel.class)
    abstract ViewModel bindsProductListViewModel(ProductListViewModel viewModel);


    @Binds
    abstract ViewModelProvider.Factory bindsViewModelFactory(BaseViewModelFactory factory);

}
