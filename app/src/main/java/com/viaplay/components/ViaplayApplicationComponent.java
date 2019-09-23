package com.viaplay.components;

import com.viaplay.connection.ViaPlayRepository;
import com.viaplay.connection.ViaPlayService;
import com.viaplay.context.ViaplayApplication;
import com.viaplay.modules.ApplicationModules;
import com.viaplay.modules.MainActivityModules;
import com.viaplay.modules.SectionDetailActivityModules;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Component( dependencies = {}, modules = {AndroidInjectionModule.class,
        SectionDetailActivityModules.class,
        MainActivityModules.class,
        ApplicationModules.class
})
@Singleton
public interface ViaplayApplicationComponent {

    void inject(ViaplayApplication application);

    ViaPlayService viaPlayService();

    ViaPlayRepository viaPlayRepository();

}
