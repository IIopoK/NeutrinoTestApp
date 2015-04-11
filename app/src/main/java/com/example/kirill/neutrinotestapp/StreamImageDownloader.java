package com.example.kirill.neutrinotestapp;

import android.content.Context;

import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by iiopok on 11.04.2015.
 */
public class StreamImageDownloader extends BaseImageDownloader {

    private static final String SCHEME_STREAM = "stream";
    private static final String STREAM_URI_PREFIX = SCHEME_STREAM + "://";

    public StreamImageDownloader(Context context) {
        super(context);
    }

    @Override
    protected InputStream getStreamFromOtherSource(String imageUri, Object extra) throws IOException {
        if (imageUri.startsWith(STREAM_URI_PREFIX)) {
            return (InputStream) extra;
        } else {
            return super.getStreamFromOtherSource(imageUri, extra);
        }
    }
}