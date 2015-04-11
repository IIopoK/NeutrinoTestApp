package com.example.kirill.neutrinotestapp.objects;

import com.google.gson.annotations.SerializedName;

/**
 * Created by iiopok on 10.04.2015.
 */
public class CreateResponseObject {
    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public boolean getSuccess(){
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
}
