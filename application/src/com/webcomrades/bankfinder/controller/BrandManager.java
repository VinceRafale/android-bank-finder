package com.webcomrades.bankfinder.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.webcomrades.bankfinder.model.Brand;

/**
 * @author Jo Somers - sayhello@josomers.be
 * @since 2013
 */

public class BrandManager {

	private Map<String, Brand> brands;
	
	public BrandManager() {
		this.brands = new HashMap<String, Brand>();
	}
	
	public BrandManager(List<Brand> brands) {
		this.brands = convertListToMap(brands);
	}
	
	public Map<String, Brand> getBrands() {
		return brands;
	}
	
	public void setBrands(List<Brand> brands) {
		this.brands = convertListToMap(brands);
	}
	
	public Brand getBrandById(String brandId) {
		return brands.get(brandId);
	}
	
	private Map<String, Brand> convertListToMap(List<Brand> brands) {
		Map<String, Brand> newBrands = new HashMap<String, Brand>();
		for (Brand brand : brands) {
			newBrands.put(brand.getId(), brand);
		}
		return newBrands;
	}
			
}
