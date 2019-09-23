package com.viaplay.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.viaplay.connection.ViaPlayRepository;
import com.viaplay.livedata.SectionDetail;
import com.viaplay.livedata.SectionPreview;

import javax.inject.Inject;

public class ViaplaySectionDetailViewModel extends ViewModel {

    private LiveData<SectionDetail> sectionDetailLiveData;
    private ViaPlayRepository repository;

    @Inject
    public ViaplaySectionDetailViewModel(ViaPlayRepository repository) {
        this.repository = repository;
    }

    public LiveData<SectionDetail> getSectionDetailLiveData(SectionPreview sectionId) {
        if(sectionDetailLiveData == null){
            sectionDetailLiveData = repository.getSectionDetailService(sectionId);
        }
        return sectionDetailLiveData;
    }

    public void refreshDetail(SectionPreview sectionId){
        repository.refreshDetail(sectionId);
    }
}
