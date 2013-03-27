package com.webcomrades.bankfinder.functions;

import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.webcomrades.bankfinder.controller.DataParser;
import com.webcomrades.bankfinder.model.Brand;

public enum BrandsInSharedPreferences {

	F;
	
	private final String identifier = "brands";
	
	public List<Brand> get(Context context) {
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
		String data = settings.getString(identifier, null);
		
		List<Brand> brands = null;
		if (data != null) {
			brands = DataParser.parseBrands(data);
		}
		
		return brands;
	}
	
	public void store(Context context, List<Brand> brands) {
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
		settings
			.edit()
			.putString(identifier, new Gson().toJson(brands))
			.commit();
	}
}
