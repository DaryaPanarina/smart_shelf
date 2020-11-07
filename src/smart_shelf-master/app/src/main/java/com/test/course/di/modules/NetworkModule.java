package com.test.course.di.modules;

import com.google.gson.Gson;
import com.test.course.data.api.UserApi;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.test.course.data.ApiParams.BASE_URL;

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
