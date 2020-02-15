package tech.coderhub.greenapp.di;

import android.app.Application;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import tech.coderhub.greenapp.di.network.ApiModule;
import tech.coderhub.basic.helper.CacheHelper;


@Module(includes = ApiModule.class)
public abstract class AppModule {
    @Singleton
    @Provides
    static CacheHelper provideCache(Application application){
        return new CacheHelper(application);
    }
}
