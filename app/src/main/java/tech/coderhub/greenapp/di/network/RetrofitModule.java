package tech.coderhub.greenapp.di.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tech.coderhub.basic.network.AccessDetails;

@Module(includes = OkHttModule.class)
public class RetrofitModule {

    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttpClient,GsonConverterFactory converterFactory){
        return  new Retrofit.Builder()
                .baseUrl(AccessDetails.serviceurl)
                .addConverterFactory(converterFactory)
                .client(okHttpClient)
                .build();
    }



    @Provides
    @Singleton
    GsonConverterFactory provideGsonConverterFactory(Gson gson){
        return  GsonConverterFactory.create(gson);
    }

    @Singleton
    @Provides
    Gson provideGson(){
        GsonBuilder builder = new GsonBuilder();
        return builder.create();
    }
}
