package com.viaplay.modules;

import com.viaplay.activities.MainActivity;
import com.viaplay.components.MainActivitySubcomponent;

import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {MainActivitySubcomponent.class})
public class MainActivityModules {

    @Provides
    @IntoMap
    @ClassKey(MainActivity.class)
    public AndroidInjector.Factory<?> bindMainActivityFactory(MainActivitySubcomponent.Builder builder){
        return builder;
    }

}
