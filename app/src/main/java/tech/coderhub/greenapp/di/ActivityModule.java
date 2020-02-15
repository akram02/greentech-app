package tech.coderhub.greenapp.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import tech.coderhub.auth.changePasswordScreen.ChangePasswordActivity;
import tech.coderhub.auth.forgotPasswordScreen.ForgotPasswordActivity;
import tech.coderhub.auth.loginScreen.LoginActivity;
import tech.coderhub.auth.profileDetailsScreen.ProfileDetailsActivity;
import tech.coderhub.auth.profileEditScreen.ProfileEditActivity;
import tech.coderhub.auth.registerScreen.RegisterActivity;
import tech.coderhub.greenapp.ui.main.MainActivity;
import tech.coderhub.basic.di.PerActivity;

@Module
public abstract class ActivityModule {
    @PerActivity
    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();

    @PerActivity
    @ContributesAndroidInjector
    abstract ForgotPasswordActivity contributeForgotPasswordActivity();

    @PerActivity
    @ContributesAndroidInjector
    abstract ProfileDetailsActivity contributeProfileDetailsActivity();

    @PerActivity
    @ContributesAndroidInjector
    abstract LoginActivity contributeLoginActivity();

    @PerActivity
    @ContributesAndroidInjector
    abstract RegisterActivity contributeRegisterActivity();

    @PerActivity
    @ContributesAndroidInjector
    abstract ProfileEditActivity contributeProfileEditActivity();

    @PerActivity
    @ContributesAndroidInjector
    abstract ChangePasswordActivity contributeChangePasswordActivity();
}
