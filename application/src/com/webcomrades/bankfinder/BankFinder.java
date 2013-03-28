package com.webcomrades.bankfinder;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.preference.PreferenceManager;

import com.webcomrades.bankfinder.BankFinderGlobals.Mode;
import com.webcomrades.bankfinder.controller.BrandManager;
import com.webcomrades.bankfinder.controller.ErrorHandler;
import com.webcomrades.bankfinder.controller.ImageViewController;
import com.webcomrades.bankfinder.functions.GetBrandsUpdater;

/**
 * @author Jo Somers - sayhello@josomers.be
 * @since 2013
 */

public class BankFinder extends Application {
	
	private static ImageViewController mImageViewController;
	private static BrandManager mBrandsManager;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		mImageViewController = new ImageViewController(getApplicationContext(), BankFinderGlobals.HTTP + BankFinderGlobals.getBaseUrl());
		mBrandsManager = new BrandManager(getApplicationContext());
				
		saveVersionCode();
		getBrandsFromServer();
	}

	private void getBrandsFromServer() {
		GetBrandsUpdater.F.apply(getApplicationContext());
	}

	private void saveVersionCode() {
		try {
			PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			PreferenceManager.getDefaultSharedPreferences(this).edit().putInt(BankFinderGlobals.PREFERENCE_VERSION_CODE, packageInfo.versionCode);
		} catch (NameNotFoundException e) {
			ErrorHandler.getInstance().handleError(getApplicationContext(), e, false);
		}
	}
	
	public static ImageViewController getImageViewController() {
		return mImageViewController;
	}
	
	public static BrandManager getBrandsManager() {
		return mBrandsManager;
	}

	public static boolean inProductionMode() {
		return BankFinderGlobals.MODE == Mode.PROD;
	}
}
