package com.webcomrades.bankfinder.datafetcher;

import java.io.IOException;
import java.io.InputStream;

public interface DataFetcher {
	
	public interface ResponseHandler {
		public String handleResponse(InputStream input) throws IOException;
	}
	
	public String get(ResponseHandler responseHandler, String location) throws IOException;
	
	public String post(ResponseHandler responseHandler, String location, String body) throws IOException;

}
