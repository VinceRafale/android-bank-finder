package com.webcomrades.bankfinder.model;

/**
 * @author Jo Somers - sayhello@josomers.be
 * @since 2013
 */

public class Bank {

	public String company;
	public String name;
	public String address;
	public String latitude;
	public String longitude;
	public String additionalInfo;

	public Bank() {

	}

	@Override
	public String toString() {
		return name + ", " + address;
	}

}
