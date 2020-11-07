package com.test.fuckedcoursech.di.modules;

import com.google.gson.Gson;
import com.test.fuckedcoursech.data.api.UserApi;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.test.fuckedcoursech.data.ApiParams.BASE_URL;

@Module
public class NetworkModule {

    @Singleton
    @Provides
    public Retrofit provideRetrofitClientUpload(Gson gson) {
         return new Retrofit.Builder()
                 .baseUrl(BASE_URL)
                 .addConverterFactory(GsonConverterFactory.create(gson))
                 .build();
    }

    @Singleton
    @Provides
    public UserApi provideUserApi(Retrofit retrofit) {
        return retrofit.create(UserApi.class);
    }

}
