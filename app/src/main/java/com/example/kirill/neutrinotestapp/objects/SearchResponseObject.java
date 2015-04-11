package com.example.kirill.neutrinotestapp.objects;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by iiopok on 10.04.2015.
 */
public class SearchResponseObject {
    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("ads")
    private List<AdvertisementObject> advertisements;

    private boolean getSuccess(){
        return success;
    }

    public void setSuccess(boolean value){
        success = value;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String value){
        message = value;
    }

    public List<AdvertisementObject> getAdvertisements(){
        return advertisements;
    }

    public void setAdvertisements(List<AdvertisementObject> value){
        advertisements = value;
    }
}
