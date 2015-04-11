package com.example.kirill.neutrinotestapp;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.kirill.neutrinotestapp.APIInterfaces.IAdvertisementSetRestAPI;
import com.example.kirill.neutrinotestapp.APIInterfaces.IUserSetRestAPI;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by iiopok on 10.04.2015.
 */
public class RestClient {
    private synchronized static RestAdapter getRestAdapter(final Context context){
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.preferences_name), context.MODE_PRIVATE);
                request.addHeader("UserId", sharedPreferences.getString(context.getString(R.string.user_id), null));
            }
        };

        return new RestAdapter.Builder()
                .setRequestInterceptor(requestInterceptor)
                .setEndpoint(context.getString(R.string.api_URL))
                .setClient(new OkClient(new OkHttpClient()))
//                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
    }

    public synchronized static IUserSetRestAPI getUserService(Context context) {
        return getRestAdapter(context).create(IUserSetRestAPI.class);
    }

    public synchronized static IAdvertisementSetRestAPI getAdvertisementService(Context context) {
        return getRestAdapter(context).create(IAdvertisementSetRestAPI.class);
    }
}
