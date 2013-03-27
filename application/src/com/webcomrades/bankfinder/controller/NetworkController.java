package com.webcomrades.bankfinder.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.webcomrades.bankfinder.BankFinderGlobals;
import com.webcomrades.bankfinder.controller.DataFetcher.ResponseHandler;
import com.webcomrades.bankfinder.model.Bank;
import com.webcomrades.bankfinder.model.Brand;

/**
 * @author Jo Somers - sayhello@josomers.be
 * @since 2013
 */

public class NetworkController {

	private final static int mConnectTimeOut = 10000;
	private final static int mReadTimeOut = 30000;
	
	private static String getUrl(String path) {
		return BankFinderGlobals.HTTP + BankFinderGlobals.getBaseUrl() + BankFinderGlobals.API + path;
	}
	
	public static List<Bank> getBanksFromServer() throws IOException {
		String url = getUrl(BankFinderGlobals.PATH_BANK);
				
		return DataParser.parseBanks(DataFetcher.getFromServer(new ResponseHandler() {
			@Override
			public String handleResponse(InputStream input) throws IOException {
				return IOUtils.toString(input);
			}
		}, url, mConnectTimeOut, mReadTimeOut));
	}
	
	public static List<Brand> getBrandsFromServer() throws IOException {
		String url = getUrl(BankFinderGlobals.PATH_BRAND);
		
		return DataParser.parseBrands(DataFetcher.getFromServer(new ResponseHandler() {
			@Override
			public String handleResponse(InputStream input) throws IOException {
				return IOUtils.toString(input);
			}
		}, url, mConnectTimeOut, mReadTimeOut));
	}
	
}
