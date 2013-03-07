package com.webcomrades.bankfinder.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.webcomrades.bankfinder.controller.DataFetcher.ResponseHandler;
import com.webcomrades.bankfinder.model.Bank;

/**
 * @author Jo Somers - sayhello@josomers.be
 * @since 2013
 */

public class NetworkController {

	private static int connectTimeOut = 10000;
	private static int readTimeOut = 30000;
	
	private DataFetcher dataFetcher;
	private DataParser dataParser;
	
	public NetworkController() {
		this.dataParser = new DataParser();
		this.dataFetcher = new DataFetcher();
	}
	
	public List<Bank> getBanksFromServer(String path) throws IOException {
		return dataParser.parseBanks(dataFetcher.getFromServer(new ResponseHandler() {
			@Override
			public String handleResponse(InputStream input) throws IOException {
				return IOUtils.toString(input);
			}
		}, path, connectTimeOut, readTimeOut));
	}
	
}
