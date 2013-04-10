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

	public Bank(String name, String address, String brandId) {
		this.id = UUID.randomUUID().toString();
		this.brandId = brandId;
		this.name = name;
		this.address = address;
	}
	
	public Brand getBrand() {
		return BankFinder.getBrandsManager().getBrandById(brandId);
	}

	@Override
	public String toString() {
		return name + ", " + address;
	}

}
