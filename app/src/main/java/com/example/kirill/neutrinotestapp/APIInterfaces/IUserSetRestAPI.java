package com.example.kirill.neutrinotestapp.APIInterfaces;

import com.example.kirill.neutrinotestapp.objects.UserObject;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by iiopok on 10.04.2015.
 */
public interface IUserSetRestAPI {

    @FormUrlEncoded
    @POST("/User/Login")
    public void login(@Field("login") String login,
                      @Field("password") String password,
                      Callback<UserObject> callback);

    @FormUrlEncoded
    @POST("/User/Register")
    public void registration(@Field("login") String login,
                      @Field("password") String password,
                      Callback<UserObject> callback);
}
