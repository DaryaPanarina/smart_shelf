package com.test.fuckedcoursech.di;

import android.app.Activity;
import com.test.fuckedcoursech.di.modules.NetworkModule;
import com.test.fuckedcoursech.di.modules.RepositoryModule;

import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(
        modules = {
                NetworkModule.class,
                RepositoryModule.class
        }
)
public interface AppComponent {
    public void inject(Activity activity);
}