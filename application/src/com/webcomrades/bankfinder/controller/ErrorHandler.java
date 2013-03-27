package com.webcomrades.bankfinder.controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * @author Jo Somers - sayhello@josomers.be
 * @since 2013
 */

public class ErrorHandler {

	private static ErrorHandler errorController;
	
	private ErrorHandler() {
		
	}
	
	public static ErrorHandler getInstance() {
		if (errorController == null) {
			errorController = new ErrorHandler();
		}
		
		return errorController;
	}
	
	public void handleError(Context context, Throwable error, boolean showError) {
		if (showError) Toast.makeText(context, "Oops. Something went wrong!", Toast.LENGTH_SHORT).show();
		
		Log.e("ErrorHandler", error.toString());
		
		// TODO: create an implementation of this method!
		// log the exception and add it to our exception database.
	}
	
}
