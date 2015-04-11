package com.example.kirill.neutrinotestapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.kirill.neutrinotestapp.objects.AdvertisementItem;
import com.example.kirill.neutrinotestapp.objects.AdvertisementObject;
import com.example.kirill.neutrinotestapp.objects.PositionAndBIS;
import com.example.kirill.neutrinotestapp.objects.SearchResponseObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class SearchAdvertisementActivity extends Activity {

    EditText etSearch;
    ListView lvAdvertisements;
    List<AdvertisementItem> items = new ArrayList<>();
    AdvertisementAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_advertisement);
        etSearch = (EditText)findViewById(R.id.etSearch);
        lvAdvertisements = (ListView)findViewById(R.id.lvAdvertisements);
        adapter = new AdvertisementAdapter(SearchAdvertisementActivity.this, items);
        lvAdvertisements.setAdapter(adapter);
        getAds("");
        Button btnCreate = (Button)findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchAdvertisementActivity.this, CreateAdvertisementActivity.class);
                startActivity(intent);
            }
        });
        Button btnSearch = (Button)findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = String.valueOf(etSearch.getText());
                getAds(search);
            }
        });
    }

    private void getAds(String search){
        RestClient.getAdvertisementService(SearchAdvertisementActivity.this).searchAdvertisement(search, new Callback<SearchResponseObject>() {
            @Override
            public void success(SearchResponseObject searchResponseObject, Response response) {
                List<AdvertisementObject> advertisementObjects = searchResponseObject.getAdvertisements();
                for (AdvertisementObject advertisementObject: advertisementObjects){
                    AdvertisementItem item = new AdvertisementItem(advertisementObject.getId(),
                            advertisementObject.getName(),
                            advertisementObject.getDescription(),
                            advertisementObject.getCreatedAt());
                    items.add(item);
                    adapter.notifyDataSetChanged();
                }

                for(final AdvertisementItem item: items){
                    RestClient.getAdvertisementService(SearchAdvertisementActivity.this).getImage(item.getId(), new Callback<Response>() {
                        @Override
                        public void success(Response response, Response response2) {
                            try {
                                item.setStream(response.getBody().in());
                                adapter.notifyDataSetChanged();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {

                        }
                    });
                }

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
