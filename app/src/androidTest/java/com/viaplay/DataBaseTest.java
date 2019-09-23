package com.viaplay;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.Nullable;

import com.viaplay.database.SectionDao;
import com.viaplay.database.ViaPlayDataBase;
import com.viaplay.livedata.SectionDetail;
import com.viaplay.livedata.SectionPreview;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4.class)
public class DataBaseTest {

    private SectionDao sectionDao;
    private ViaPlayDataBase dataBase;

    @Mock
    Observer<List<SectionPreview>> spObserver;

    @Mock
    Observer<SectionDetail> sdObserver;

    private Context application;

    @Before
    public void init(){
        application = ApplicationProvider.getApplicationContext();
        dataBase = Room.inMemoryDatabaseBuilder(application, ViaPlayDataBase.class).allowMainThreadQueries().build();
        sectionDao = dataBase.getSectionDao();
    }

    @After
    public void closeDb() throws IOException {
        dataBase.close();
    }

    @Test
    public void testInsertionSectionPreview() {
        ArrayList<SectionPreview> sectionPreviews = new ArrayList<>();
        final SectionPreview sp = new SectionPreview();
        sp.setName("movie");
        sp.setType("vod");
        sp.setHref("https://content.viaplay.se/androiddash-se/film{?dtg}");
        sp.setTemplated(true);
        sp.setId("2037b330-d411-11e2-8b8b-0800200c9a66");
        sp.setTitle("Film");
        sectionPreviews.add(sp);

        sectionDao.insertSectionPreviews(sectionPreviews);

        LiveData<List<SectionPreview>> listLiveData = sectionDao.getSectionPreviews();
        listLiveData.observeForever(new Observer<List<SectionPreview>>() {
            @Override
            public void onChanged(@Nullable List<SectionPreview> sectionPreviews) {
                assertThat(sectionPreviews.size() , equalTo(1));
                assertThat(sectionPreviews.get(0).getId() , equalTo(sp.getId()));
            }
        });
    }

    @Test
    public void testInsertionSectionDetail(){
        final SectionDetail sd = new SectionDetail();
        sd.setTitle("movie");
        sd.setSectionId("2037b330-d411-11e2-8b8b-0800200c9a66");
        sd.setHref("https://content.viaplay.se/androiddash-se/film{?dtg}");
        sd.setPageType("");
        sd.setDescription("");

        sectionDao.insertSectionDetail(sd);

        final LiveData<SectionDetail> listLiveData = sectionDao.getSectionById("2037b330-d411-11e2-8b8b-0800200c9a66");
        listLiveData.observeForever(new Observer<SectionDetail>() {
            @Override
            public void onChanged(@Nullable SectionDetail sectionDetail) {
                assertThat(sectionDetail, not(nullValue()));
                assertThat(sectionDetail.getSectionId() , equalTo(sd.getSectionId()));
            }
        });
    }
}
