package com.example.kirill.neutrinotestapp.objects;

import com.example.kirill.neutrinotestapp.SupportMethods;
import com.google.gson.annotations.SerializedName;

/**
 * Created by iiopok on 10.04.2015.
 */
public class UserObject {
    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("id")
    private String id;

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

    public String getId(){
        return id;
    }

    public void setId(String value){
        id = value;
    }
}
