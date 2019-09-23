package com.viaplay.modules;

import com.viaplay.activities.SectionDetailActivity;
import com.viaplay.components.SectionDetailSubcomponent;

import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {SectionDetailSubcomponent.class})
public class SectionDetailActivityModules {

    @Provides
    @IntoMap
    @ClassKey(SectionDetailActivity.class)
    public AndroidInjector.Factory<?> bindSectionActivityFactory(SectionDetailSubcomponent.Builder builder){
        return builder;
    }

}
