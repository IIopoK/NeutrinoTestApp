package com.example.kirill.neutrinotestapp.objects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by iiopok on 10.04.2015.
 */
public class AdvertisementObject {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("createdAt")
    private String createdAt;

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
}
