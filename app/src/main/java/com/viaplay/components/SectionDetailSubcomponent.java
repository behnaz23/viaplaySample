package com.viaplay.components;

import com.viaplay.activities.SectionDetailActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent
public interface SectionDetailSubcomponent extends AndroidInjector<SectionDetailActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<SectionDetailActivity>{

    }

}
