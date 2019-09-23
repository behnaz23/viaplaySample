package com.viaplay.connection;


import android.arch.lifecycle.LiveData;
import android.support.annotation.WorkerThread;

import com.viaplay.database.SectionDao;
import com.viaplay.jsons.Section;
import com.viaplay.jsons.SectionRootData;
import com.viaplay.livedata.SectionDetail;
import com.viaplay.livedata.SectionPreview;
import com.viaplay.utils.SectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class ViaPlayRepository {

    private ViaPlayService webService;
    private SectionDao sectionDao;
    private Executor executor =
            new ThreadPoolExecutor(10 ,
                    10, 30,
                    TimeUnit.SECONDS,
                    new LinkedBlockingDeque<Runnable>());

    @Inject
    ViaPlayRepository(ViaPlayService webService, SectionDao sectionDao) {
        this.webService = webService;
        this.sectionDao = sectionDao;
    }

    public void refreshSectionList(){
        webService.getSections().enqueue(new Callback<SectionRootData>() {
            @Override
            public void onResponse(Call<SectionRootData> call, Response<SectionRootData> response) {
                SectionRootData sectionRootData = response.body();

                final List<SectionPreview> sectionPreviews = new ArrayList<>();
                assert sectionRootData != null;
                for (Section section : sectionRootData.getLink().getSectionList()) {
                    SectionPreview sectionItem = new SectionPreview();
                    sectionItem.setId(section.getId());
                    sectionItem.setTitle(section.getTitle());
                    sectionItem.setHref(section.getHref());
                    sectionItem.setName(section.getName());
                    sectionItem.setTemplated(sectionItem.getTemplated());
                    sectionItem.setType(sectionItem.getType());
                    sectionPreviews.add(sectionItem);
                }

                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        sectionDao.insertSectionPreviews(sectionPreviews);
                    }
                });

            }

            @Override
            public void onFailure(Call<SectionRootData> call, Throwable t) {

            }
        });
    }

    public LiveData<List<SectionPreview>> getSectionListService() {
        LiveData<List<SectionPreview>> sectionLiveDataLiveData = sectionDao.getSectionPreviews();
        refreshSectionList();
        return sectionLiveDataLiveData;
    }

    @WorkerThread
    public LiveData<SectionDetail> getSectionDetailService(SectionPreview preview) {
        LiveData<SectionDetail> sectionLiveDataLiveData = sectionDao.getSectionById(preview.getId());
        refreshDetail(preview);
        return sectionLiveDataLiveData;
    }

    public void refreshDetail(SectionPreview preview) {
        String href = SectionUtils.extractSectionUri(preview.getHref());
        webService.getSectionDetail(href).enqueue(new Callback<SectionRootData>() {
            @Override
            public void onResponse(Call<SectionRootData> call, Response<SectionRootData> response) {
                SectionRootData sectionRootData = response.body();

                final SectionDetail sectionDetail = new SectionDetail();
                sectionDetail.setDescription(sectionRootData.getDescription());
                sectionDetail.setHref(sectionRootData.getHref());
                sectionDetail.setPageType(sectionRootData.getPageType());
                sectionDetail.setSectionId(sectionRootData.getSectionId());
                sectionDetail.setTitle(sectionRootData.getTitle());

                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        sectionDao.insertSectionDetail(sectionDetail);
                    }
                });

            }

            @Override
            public void onFailure(Call<SectionRootData> call, Throwable t) {

            }
        });
    }

}
