package com.webcomrades.bankfinder.datafetcher;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import de.akquinet.android.androlog.Log;

/**
 * @author Jo Somers - sayhello@josomers.be
 * @since 2013
 */

public class HttpURLDataFetcher implements DataFetcher {

	private static final String TAG = HttpURLDataFetcher.class.getSimpleName();
	
	private final int mConnectTimeout;
	private final int mReadTimeout;
	private final String mBaseURL;
	
	public HttpURLDataFetcher(String baseUrl, int connectTimeout, int readTimeout) {
		mConnectTimeout = connectTimeout;
		mReadTimeout = readTimeout;
		mBaseURL = baseUrl;
	}
	
	@Override
	public String get(ResponseHandler responseHandler, String location) throws IOException {
		String fullUrl = mBaseURL + location;

		Log.v(TAG, "get data from: " + fullUrl);

		URL url = new URL(fullUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setConnectTimeout(mConnectTimeout);
		connection.setReadTimeout(mReadTimeout);
		
		try {
			InputStream stream = connection.getInputStream();
			return responseHandler.handleResponse(new BufferedInputStream(stream));
		} finally {
			connection.disconnect();
		}
	}
	
	@Override
	public String post(ResponseHandler responseHandler, String location, String body) throws IOException {
		String fullUrl = mBaseURL + location;
		Log.v(TAG, "post data to: " + fullUrl);
		
		URL url = new URL(fullUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setConnectTimeout(mConnectTimeout);
		connection.setReadTimeout(mReadTimeout);
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setUseCaches(false);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json"); 
		connection.setRequestProperty("charset", "utf-8");

		try {
			DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
			outputStream.writeBytes(body);
			outputStream.flush();
			outputStream.close();

			return responseHandler.handleResponse(new BufferedInputStream(connection.getInputStream()));
		} finally {
			connection.disconnect();
		}
	}

}
