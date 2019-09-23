package com.viaplay.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;


import com.viaplay.livedata.SectionDetail;
import com.viaplay.livedata.SectionPreview;

import java.util.List;

@Dao
public interface SectionDao {

    @Query("select * from SectionDetail where sectionId like :sectionId")
    LiveData<SectionDetail> getSectionById(String sectionId);

    @Query("select * from SectionPreview")
    LiveData<List<SectionPreview>> getSectionPreviews();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSectionDetail(SectionDetail sectionLiveData);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSectionPreviews(List<SectionPreview> sectionPreviews);

}
