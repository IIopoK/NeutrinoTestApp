package com.example.kirill.neutrinotestapp.objects;

import android.graphics.Bitmap;

import java.io.InputStream;

/**
 * Created by iiopok on 11.04.2015.
 */
public class AdvertisementItem {

    private String id;
    private String name;
    private String description;
    private String createdAt;
    private InputStream stream;

    public AdvertisementItem(String id, String name, String description, String createdAt){
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
    }

    public String getId(){
        return id;
    }

    public void setId(String value){
        id = value;
    }

    public String getName(){
        return name;
    }

    public void setName(String value){
        name = value;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String value){
        description = value;
    }

    public String getCreatedAt(){
        return createdAt;
    }

    public void setCreatedAt(String value){
        createdAt = value;
    }

    public InputStream getStream(){
        return stream;
    }

    public void setStream(InputStream value){
        stream = value;
    }
}
