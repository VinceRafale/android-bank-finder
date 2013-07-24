package com.webcomrades.bankfinder;

/**
 * User: josomers
 * Date: 10/06/13
 * Time: 14:10
 */

public class BankFinderGlobals {

    private enum Mode {
        DEV, PROD
    }

    // application config
    private static final Mode MODE = Mode.PROD;
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

    // application
    public static String getBaseUrl() {
        return MODE == Mode.PROD ? BASE_URL_PROD : BASE_URL_DEV;
    }

    public static boolean inProductionMode() {
        return BankFinderGlobals.MODE == Mode.PROD;
    }

}
