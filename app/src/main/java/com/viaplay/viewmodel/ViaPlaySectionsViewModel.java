package com.viaplay.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.viaplay.connection.ViaPlayRepository;
import com.viaplay.livedata.SectionPreview;

import java.util.List;

import javax.inject.Inject;

public class ViaPlaySectionsViewModel extends ViewModel {
    private LiveData<List<SectionPreview>> sectionListData;
    private ViaPlayRepository repository;


    @Inject
    public ViaPlaySectionsViewModel(ViaPlayRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<SectionPreview>> getSectionList(){
        if(sectionListData == null){
            sectionListData = repository.getSectionListService();
        }
        return sectionListData;
    }

    public void refreshList(){
        repository.refreshSectionList();
    }

}
