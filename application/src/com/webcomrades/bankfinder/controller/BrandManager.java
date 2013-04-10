package com.webcomrades.bankfinder.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;

import com.webcomrades.bankfinder.function.BrandsInSharedPreferences;
import com.webcomrades.bankfinder.function.GetBrandsFromApplicationSettings;
import com.webcomrades.bankfinder.model.Brand;

/**
 * @author Jo Somers - sayhello@josomers.be
 * @since 2013
 */

public class BrandManager {

	private Context context;
	private Map<String, Brand> mBrands;
	
	public BrandManager(Context context) {
		this.context = context;
		
		try {
			this.mBrands = convertListToMap(GetBrandsFromApplicationSettings.F.apply(context));
		} catch (Exception e) {
			ErrorHandler.getInstance().handleError(context, e, false);
			this.mBrands = new HashMap<String, Brand>();
		}
	}
	
	public Map<String, Brand> getBrands() {
		return mBrands;
	}
	
	public void setBrands(List<Brand> brands) {
		this.mBrands = convertListToMap(brands);
		BrandsInSharedPreferences.F.store(context, new ArrayList<Brand>(brands));
	}
	
	public Brand getBrandById(String brandId) {
		return mBrands.get(brandId);
	}
	
	public Map<String, Brand> convertListToMap(List<Brand> brands) {
		Map<String, Brand> newBrands = new HashMap<String, Brand>();
		for (Brand brand : brands) {
			newBrands.put(brand.id, brand);
		}
		return newBrands;
	}
			
}
