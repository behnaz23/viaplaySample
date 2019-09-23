package com.viaplay.connection;

import com.viaplay.jsons.SectionRootData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ViaPlayService {

    @GET
    Call<SectionRootData> getSectionDetail(@Url String url);

    @GET("/androiddash-se")
    Call<SectionRootData> getSections();

}
