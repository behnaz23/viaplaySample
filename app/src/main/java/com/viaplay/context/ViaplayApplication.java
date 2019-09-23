package com.viaplay.context;

import android.app.Activity;
import android.app.Application;

import com.viaplay.components.DaggerViaplayApplicationComponent;
import com.viaplay.modules.ApplicationModules;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class ViaplayApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;


    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        DaggerViaplayApplicationComponent.builder()
                .applicationModules(new ApplicationModules(this)).build().inject(this);
    }
}
