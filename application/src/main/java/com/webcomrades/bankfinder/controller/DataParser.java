package com.webcomrades.bankfinder.controller;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.webcomrades.bankfinder.model.Bank;
import com.webcomrades.bankfinder.model.Brand;

/**
 * @author Jo Somers - sayhello@josomers.be
 * @since 2013
 */

public class DataParser {

	public static Bank parseBank(String data) {
		Gson gson = new GsonBuilder().create();
		Type rootType = new TypeToken<Bank>(){}.getType();
		
		Bank bank = gson.fromJson(data, rootType);
		if (bank == null) {
			throw new JsonSyntaxException("Error while parsing bank from JSON: " + data);
		}
		
		return bank;
	}
	
	public static List<Bank> parseBanks(String data) {
		Gson gson = new GsonBuilder().create();
		Type rootType = new TypeToken<List<Bank>>(){}.getType();
		
		List<Bank> banks = gson.fromJson(data, rootType);
		if (banks == null) {
			throw new JsonSyntaxException("Error while parsing banks from JSON: " + data);
		}
		
		return banks;
	}
	
	public static List<Brand> parseBrands(String data) {
		Gson gson = new GsonBuilder().create();
		Type rootType = new TypeToken<List<Brand>>(){}.getType();
		
		List<Brand> brands = gson.fromJson(data, rootType);
		if (brands == null) {
			throw new JsonSyntaxException("Error while parsing brands from JSON: " + data);
		}
		
		return brands;
	}

}
