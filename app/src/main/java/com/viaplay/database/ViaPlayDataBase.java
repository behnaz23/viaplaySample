package com.viaplay.database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.viaplay.livedata.SectionDetail;
import com.viaplay.livedata.SectionPreview;

@Database(entities = {SectionDetail.class, SectionPreview.class}, version = 1, exportSchema = false)
public abstract class ViaPlayDataBase extends RoomDatabase {

    public abstract SectionDao getSectionDao();

}


