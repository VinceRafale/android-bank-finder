package com.webcomrades.bankfinder.model;

/**
 * @author Jo Somers - sayhello@josomers.be
 * @since 2013
 */

public class Icon {

	private String url;
	private String fileName;

	public Icon() {

	}

	public Icon(String url, String fileName) {
		this.url = url;
		this.fileName = fileName;
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getFileName() {
		return fileName;
	}
	
}
