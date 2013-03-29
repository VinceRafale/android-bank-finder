package com.webcomrades.bankfinder.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.webcomrades.bankfinder.controller.DataFetcher.ResponseHandler;
import com.webcomrades.bankfinder.model.Bank;
import com.webcomrades.bankfinder.model.Brand;

/**
 * @author Jo Somers - sayhello@josomers.be
 * @since 2013
 */

public class NetworkController {

	private final int mConnectTimeOut;
	private final int mReadTimeOut;
	private final String mBaseUrl;
	private final String mBankPath;
	private final String mBrandPath;
	
	public NetworkController(String baseUrl, String bankPath, String brandPath, int timeout, int readtimeout) {
		this.mConnectTimeOut = timeout;
		this.mReadTimeOut = readtimeout;
		this.mBaseUrl = baseUrl;
		this.mBankPath = bankPath;
		this.mBrandPath = brandPath;
	}
	
	private String getUrl(String path) {
		return mBaseUrl + path;
	}
	
	public List<Bank> getBanksFromServer() throws IOException {
		String url = getUrl(mBankPath);
				
		return DataParser.parseBanks(DataFetcher.getFromServer(new ResponseHandler() {
			@Override
			public String handleResponse(InputStream input) throws IOException {
				return IOUtils.toString(input);
			}
		}, url, mConnectTimeOut, mReadTimeOut));
	}
	
	public List<Brand> getBrandsFromServer() throws IOException {
		String url = getUrl(mBrandPath);
		
		return DataParser.parseBrands(DataFetcher.getFromServer(new ResponseHandler() {
			@Override
			public String handleResponse(InputStream input) throws IOException {
				return IOUtils.toString(input);
			}
		}, url, mConnectTimeOut, mReadTimeOut));
	}

	public Bank postBankToServer(Bank mBank) throws IOException {
		String url = getUrl(mBankPath);
		
		return DataParser.parseBank(DataFetcher.postToServer(new ResponseHandler() {
			@Override
			public String handleResponse(InputStream input) throws IOException {
				return IOUtils.toString(input);
			}
		}, url, mConnectTimeOut, mReadTimeOut, new Gson().toJson(mBank)));
	}
	
}
