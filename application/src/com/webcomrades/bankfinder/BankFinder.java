package com.webcomrades.bankfinder;

import com.webcomrades.bankfinder.controller.ErrorController;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.preference.PreferenceManager;

/**
 * @author Jo Somers - sayhello@josomers.be
 * @since 2013
 */

public class BankFinder extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		saveVersionCode();
	}

	private void saveVersionCode() {
		try {
			PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			PreferenceManager.getDefaultSharedPreferences(this).edit().putInt(BankFinderGlobals.PREFERENCE_VERSION_CODE, packageInfo.versionCode);
		} catch (NameNotFoundException e) {
			ErrorController.getInstance().handleError(e);
		}
	}

}
