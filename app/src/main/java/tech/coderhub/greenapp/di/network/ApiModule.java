package tech.coderhub.greenapp.di.network;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = RetrofitModule.class)
public class ApiModule {
    @Singleton
    @Provides
    RestApi provideRestApi(Retrofit retrofit){
        return retrofit.create(RestApi.class);
    }
}
