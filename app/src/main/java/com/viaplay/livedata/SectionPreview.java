package com.viaplay.livedata;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity
public class SectionPreview implements Serializable {

    @PrimaryKey
    @NonNull
    private String id;

    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "href")
    private String href;
    @ColumnInfo(name = "type")
    private String type;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "templated")
    private Boolean templated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getTemplated() {
        return templated;
    }

    public void setTemplated(Boolean templated) {
        this.templated = templated;
    }
}
