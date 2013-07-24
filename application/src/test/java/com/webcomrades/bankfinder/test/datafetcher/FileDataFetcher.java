package com.webcomrades.bankfinder.test.datafetcher;

import android.content.Context;
import android.util.Log;

import com.google.common.collect.ImmutableMap;
import com.webcomrades.bankfinder.datafetcher.DataFetcher;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * User: josomers
 * Date: 10/06/13
 * Time: 14:10
 */

public class FileDataFetcher implements DataFetcher {

    private static final String TAG = FileDataFetcher.class.getSimpleName();

    private final Context context;
    private final ImmutableMap<String, Integer> rawResourceIds;

    public FileDataFetcher(Context context, ImmutableMap<String, Integer> rawResourceIds) {
        this.context = context;
        this.rawResourceIds = rawResourceIds;
    }

    @Override
    public String get(ResponseHandler responseHandler, String location) throws IOException {

        Log.v(TAG, "get from resource: " + location);

        InputStream stream = context.getResources().openRawResource(rawResourceIds.get(location));
        return responseHandler.handleResponse(new BufferedInputStream(stream));
    }

    @Override
    public String post(ResponseHandler responseHandler, String location, String body) throws IOException {
        return "";    // TODO
    }

}
