package com.webcomrades.bankfinder.controller;

/**
 * @author Jo Somers - sayhello@josomers.be
 * @since 2013
 */

public class ErrorController {

	private static ErrorController errorController;
	
	private ErrorController() {
		
	}
	
	public static ErrorController getInstance() {
		if (errorController == null) {
			errorController = new ErrorController();
		}
		
		return errorController;
	}
	
	public void handleError(Throwable error) {
		// TODO: create an implementation of this method!
		// log the exception and add it to our exception database.
	}
	
}
