package com.webcomrades.bankfinder.controller;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.webcomrades.bankfinder.model.Bank;

/**
 * @author Jo Somers - sayhello@josomers.be
 * @since 2013
 */

public class DataParser {

	public DataParser() {
		
	}
	
	public List<Bank> parseBanks(String data) {
		Gson gson = new GsonBuilder().create();
		Type rootType = new TypeToken<List<Bank>>(){}.getType();
		
		List<Bank> banks = gson.fromJson(data, rootType);
		if(banks == null) {
			throw new JsonSyntaxException("Error while parsing data from JSON: " + data);
		}
		
		return banks;
	}
	
}
