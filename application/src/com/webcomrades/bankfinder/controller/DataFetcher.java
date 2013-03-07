package com.webcomrades.bankfinder.controller;

import java.io.BufferedInputStream;
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

	public DataFetcher() {

	}

	public String getFromServer(ResponseHandler responseHandler,
			String fullUrl, int connectTimeout, int readTimeout)
			throws IOException {

		Log.d(TAG, "get data from: " + fullUrl);

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

			return responseHandler.handleResponse(new BufferedInputStream(
					stream));
		} catch (IOException e) {
			ErrorController.getInstance().handleError(e);
		} finally {
			connection.disconnect();
		}

		return null;
	}

}
