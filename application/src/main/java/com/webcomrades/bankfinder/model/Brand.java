package com.webcomrades.bankfinder.model;

import java.util.UUID;

/**
 * @author Jo Somers - sayhello@josomers.be
 * @since 2013
 */

public class Brand {

	private String id;
	private String name;
	private Icon icon;
	
	public Brand() {
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public Icon getIcon() {
		return icon;
	}

	@Override
	public String toString() {
		return name;
	}
		
}
