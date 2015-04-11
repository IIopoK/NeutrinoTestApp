package com.example.kirill.neutrinotestapp.APIInterfaces;

import com.example.kirill.neutrinotestapp.objects.CreateResponseObject;
import com.example.kirill.neutrinotestapp.objects.SearchResponseObject;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.http.Streaming;
import retrofit.mime.TypedFile;

/**
 * Created by iiopok on 10.04.2015.
 */
public interface IAdvertisementSetRestAPI {

    @Multipart
    @POST("/Advertisement/Create")
    public void createAdvertisement(@Part("name") String name,
                                    @Part("description") String description,
                                    @Part("image") TypedFile image,
                                    Callback<CreateResponseObject> callback);

    @GET("/Advertisement/Search")
    public void searchAdvertisement(@Query("query") String query,
                                    Callback<SearchResponseObject> callback);

    @GET("/Advertisement/image/{id}")
    @Streaming
    public void getImage(@Path("id") String advertisementId,
                                    Callback<Response> callback);
}
