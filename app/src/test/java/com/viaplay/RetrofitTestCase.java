package com.viaplay;

import com.viaplay.connection.ViaPlayService;
import com.viaplay.jsons.Section;
import com.viaplay.jsons.SectionRootData;
import com.viaplay.utils.SectionUtils;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static com.google.common.truth.Truth.assertThat;


public class RetrofitTestCase {

    private Retrofit retrofit;

    @Before
    public void initRetrofit(){
        retrofit = new Retrofit.Builder().baseUrl("https://content.viaplay.se/")
                .addConverterFactory(JacksonConverterFactory.create()).build();
    }


    @Test
    public  void testSectionUtilsError() {
        String uri = SectionUtils.extractSectionUri("sections/serier");
        assertThat(uri).isEqualTo("sections/serier");
    }

    @Test
    public void testSectionUtils() {
        String uri = SectionUtils.extractSectionUri("https://content.viaplay.se/androiddash-se/serier{?dtg}");
        assertThat(uri).isEqualTo("/androiddash-se/serier");
    }

    @Test(timeout = 5000)
    public void testMainService() throws IOException {
        ViaPlayService playService = retrofit.create(ViaPlayService.class);
        Call<SectionRootData> dataCall =  playService.getSections();
        SectionRootData rootLiveData = dataCall.execute().body();
        assertThat(rootLiveData).isNotNull();
        assertThat(rootLiveData.getDescription()).isNotEmpty();
        assertThat(rootLiveData.getTitle()).isNotEmpty();
        assertThat(rootLiveData.getLink()).isNotNull();
        assertThat(rootLiveData.getLink().getSectionList()).isNotEmpty();
    }

    @Test
    public void testSection() throws IOException {
        ViaPlayService playService = retrofit.create(ViaPlayService.class);
        Call<SectionRootData> dataCall =  playService.getSections();

        SectionRootData rootLiveData = dataCall.execute().body();
        assertThat(rootLiveData).isNotNull();
        assertThat(rootLiveData.getDescription()).isNotEmpty();
        assertThat(rootLiveData.getTitle()).isNotEmpty();
        assertThat(rootLiveData.getLink()).isNotNull();
        assertThat(rootLiveData.getLink().getSectionList()).isNotEmpty();

        Section section = rootLiveData.getLink().getSectionList().get(0);
        String url = new URL(section.getHref()).getFile().replace("{?dtg}", "");
        Call<SectionRootData> sectionRootDataCall = playService.getSectionDetail(url);

        SectionRootData sectionLiveData = sectionRootDataCall.execute().body();
        assertThat(sectionLiveData).isNotNull();
        assertThat(sectionLiveData.getDescription()).isNotEmpty();
        assertThat(sectionLiveData.getTitle()).isNotEmpty();
        assertThat(sectionLiveData.getPageType()).isEqualTo("section");
        assertThat(sectionLiveData.getSectionId()).isEqualTo(section.getId());
    }

}
