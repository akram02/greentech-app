package tech.coderhub.greenapp.di;

import android.app.Application;


import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import tech.coderhub.greenapp.MyApplication;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        ActivityModule.class,
        ViewModelModule.class,
})
public interface ApplicationComponent {

    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder provideApplication(Application application);
        ApplicationComponent build();
    }

    void inject(MyApplication application);
}
