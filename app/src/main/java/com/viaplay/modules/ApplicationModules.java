package com.viaplay.modules;

import android.arch.persistence.room.Room;

import com.viaplay.connection.ViaPlayService;
import com.viaplay.context.ViaplayApplication;
import com.viaplay.database.SectionDao;
import com.viaplay.database.ViaPlayDataBase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Module
public class ApplicationModules {

    private ViaplayApplication viaplayApplication;

    public ApplicationModules(ViaplayApplication viaplayApplication) {
        this.viaplayApplication = viaplayApplication;
    }

    @Provides
    @Singleton
    public ViaPlayService provideWebService(Retrofit retrofit){
        return retrofit.create(ViaPlayService.class);
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://content.viaplay.se/")
                .addConverterFactory(JacksonConverterFactory.create()).build();
        return retrofit;
    }

    @Provides
    @Singleton
    public ViaPlayDataBase provideDataBase(){
        ViaPlayDataBase dataBase =
                Room.databaseBuilder(viaplayApplication, ViaPlayDataBase.class,viaplayApplication.getPackageName()).build();
        return dataBase;
    }

    @Provides
    @Singleton
    public SectionDao provideSectionDao(ViaPlayDataBase database){
        return database.getSectionDao();
    }


}
