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
	public static final Mode MODE = Mode.DEV;

	// shared preferences
	public static final String PREFERENCE_VERSION_CODE = "version_code";

}
