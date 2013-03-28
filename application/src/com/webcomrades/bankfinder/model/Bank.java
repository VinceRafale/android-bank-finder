package com.webcomrades.bankfinder.model;

import java.util.UUID;

import com.webcomrades.bankfinder.BankFinder;

/**
 * @author Jo Somers - sayhello@josomers.be
 * @since 2013
 */

public class Bank {

	public String id;
	public String brandId;
	public String name;
	public String address;

	public Bank() {
		this.id = UUID.randomUUID().toString();
		this.brandId = "0c2fc1ae-ca11-4fdc-9e62-757ec60147a7"; // bnp
	}
	
	public Bank(String name, String address, String additionalInfo) {
		this();
		this.name = name;
		this.address = additionalInfo;
	}
	
	public Brand getBrand() {
		return BankFinder.getBrandsManager().getBrandById(brandId);
	}

	@Override
	public String toString() {
		return name + ", " + address;
	}

}
