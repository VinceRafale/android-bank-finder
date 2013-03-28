package com.webcomrades.bankfinder.controller;

import android.content.Context;
import android.widget.Toast;

import com.webcomrades.bankfinder.BankFinder;
import com.webcomrades.bankfinder.R;

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
	
	public void handleError(Context context, Throwable e, boolean showError) {
		if (showError) Toast.makeText(context, context.getString(R.string.tError), Toast.LENGTH_SHORT).show();
				
		if (!BankFinder.inProductionMode()) {
			e.printStackTrace();
		}
		
		// TODO: create an implementation of this method!
		// log the exception and add it to our exception database.
	}
	
}
