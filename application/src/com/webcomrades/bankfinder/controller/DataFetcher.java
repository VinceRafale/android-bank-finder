package com.webcomrades.bankfinder.controller;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.client.HttpResponseException;

import de.akquinet.android.androlog.Log;

/**
 * @author Jo Somers - sayhello@josomers.be
 * @since 2013
 */

public class DataFetcher {

	private static final String TAG = DataFetcher.class.getSimpleName();

	public interface ResponseHandler {
		public String handleResponse(InputStream input) throws IOException;
	}
	
	public static String getFromServer(ResponseHandler responseHandler,
			String fullUrl, int connectTimeout, int readTimeout)
			throws IOException {

		Log.v(TAG, "get data from: " + fullUrl);

		URL url = new URL(fullUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setConnectTimeout(connectTimeout);
		connection.setReadTimeout(readTimeout);
		
		try {
			InputStream stream = connection.getInputStream();

			if (connection.getResponseCode() >= 400) {
				throw new HttpResponseException(connection.getResponseCode(),
						connection.getResponseMessage());
			}

			return responseHandler.handleResponse(new BufferedInputStream(stream));
		} finally {
			connection.disconnect();
		}
	}
	
	public static String postToServer(ResponseHandler responseHandler, 
			String fullUrl, int connectTimeout, int readTimeout, String body) throws IOException {
		
		Log.v(TAG, "post data to: " + fullUrl);
		
		URL url = new URL(fullUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setConnectTimeout(connectTimeout);
		connection.setReadTimeout(readTimeout);
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setUseCaches(false);
		connection.setChunkedStreamingMode(0);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json"); 
		connection.setRequestProperty("charset", "utf-8");

		try {
			DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());

//			if (connection.getResponseCode() >= 400) {
//				throw new HttpResponseException(connection.getResponseCode(),
//						connection.getResponseMessage());
//			}
			
			outputStream.writeBytes(body);
			outputStream.flush();
			outputStream.close();

			return responseHandler.handleResponse(new BufferedInputStream(connection.getInputStream()));
		} finally {
			connection.disconnect();
		}
	}

}
