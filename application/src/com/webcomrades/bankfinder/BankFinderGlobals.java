package com.webcomrades.bankfinder;

/**
 * @author Jo Somers - sayhello@josomers.be
 * @since 2013
 */

public class BankFinderGlobals {

	public enum Mode {
		DEV, PROD
	}

	// application config
	public static final Mode MODE = Mode.PROD;
	public static final String ACRA_KEY = "dG55MGI4VnNad2F2RVBHUXpwY01SV2c6MA";
	
	// server configuration
	public static final String HTTP = "https://";
	public static final int PORT = 443;
	public static final String API = "/api";
	private static final String BASE_URL_PROD = "banker.playground.webcomrad.es";
	private static final String BASE_URL_DEV = "banker.playground.webcomrad.es"; 
	
	public static final String PATH_BANK = "/bank";
	public static final String PATH_BRAND = "/brand";
	
	// shared preferences
	public static final String PREFERENCE_VERSION_CODE = "version_code";
	
	// information
	public static String getBaseUrl() {
		return MODE == Mode.PROD ? BASE_URL_PROD : BASE_URL_DEV;
	}

}
