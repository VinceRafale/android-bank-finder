package com.webcomrades.bankfinder.model;

import java.util.UUID;

/**
 * @author Jo Somers - sayhello@josomers.be
 * @since 2013
 */

public class Brand {

	public String id;
	public String name;
	public Icon icon;
	
	public Brand() {
		this.id = UUID.randomUUID().toString();
	}

	@Override
	public String toString() {
		return name;
	}
		
}
