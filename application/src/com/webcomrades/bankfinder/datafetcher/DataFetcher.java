package com.webcomrades.bankfinder.datafetcher;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Jo Somers - sayhello@josomers.be
 * @since 2013
 */

public interface DataFetcher {
	
	public interface ResponseHandler {
		public String handleResponse(InputStream input) throws IOException;
	}
	
	public String get(ResponseHandler responseHandler, String location) throws IOException;
	
	public String post(ResponseHandler responseHandler, String location, String body) throws IOException;

}
