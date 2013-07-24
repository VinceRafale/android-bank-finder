package com.webcomrades.bankfinder;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources.NotFoundException;
import android.preference.PreferenceManager;

import com.google.common.collect.Lists;
import com.webcomrades.bankfinder.controller.BrandManager;
import com.webcomrades.bankfinder.controller.DataController;
import com.webcomrades.bankfinder.controller.ErrorHandler;
import com.webcomrades.bankfinder.controller.ImageViewController;
import com.webcomrades.bankfinder.datafetcher.HttpURLDataFetcher;
import com.webcomrades.bankfinder.function.GetBrandsFromApplicationSettings;
import com.webcomrades.bankfinder.function.GetBrandsUpdater;
import com.webcomrades.bankfinder.model.Brand;

import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

import java.io.IOException;
import java.util.List;

/**
 * User: josomers
 * Date: 10/06/13
 * Time: 14:10
 */

@ReportsCrashes(formKey = "")
public class BankFinder extends Application {

    private static ImageViewController imageViewController;
    private static BrandManager brandsManager;
    private static DataController networkController;
    private static ErrorHandler errorHandler;
    private static boolean hasSuccesFullAcraInstance = true;

    @Override
    public void onCreate() {
        super.onCreate();

        initAcra();

        imageViewController = new ImageViewController(getApplicationContext(), BankFinderGlobals.HTTP + BankFinderGlobals.getBaseUrl());
        brandsManager = new BrandManager(getBrandsFromSettings());
        networkController = new DataController(BankFinderGlobals.PATH_BANK, BankFinderGlobals.PATH_BRAND, new HttpURLDataFetcher(getBaseUrl(), 10000, 30000));
        errorHandler = new ErrorHandler(BankFinderGlobals.inProductionMode());

        saveVersionCode();
        getBrandsFromServer();
    }

    private void initAcra() {
        try {
            ACRA.getConfig().setFormKey(BankFinderGlobals.ACRA_KEY);
            ACRA.getConfig().setCustomReportContent(new ReportField[]{ReportField.APP_VERSION_CODE, ReportField.APP_VERSION_NAME,
                    ReportField.ANDROID_VERSION, ReportField.BRAND, ReportField.PHONE_MODEL, ReportField.PRODUCT,
                    ReportField.STACK_TRACE, ReportField.DISPLAY, ReportField.INITIAL_CONFIGURATION, ReportField.USER_CRASH_DATE, ReportField.CUSTOM_DATA});
            ACRA.getConfig().setMode(ReportingInteractionMode.SILENT);
            ACRA.init(this);
        } catch (Exception e) {
            hasSuccesFullAcraInstance = false;
            //  there's a small (nullpointer) exception that occurs with some application instances. just catch them, we can't report it to our systems.
        }
    }

    private static String getBaseUrl() {
        return BankFinderGlobals.HTTP + BankFinderGlobals.getBaseUrl() + BankFinderGlobals.API;
    }

    private List<Brand> getBrandsFromSettings() {
        List<Brand> brands = Lists.newArrayList();

        try {
            brands = GetBrandsFromApplicationSettings.F.apply(getApplicationContext());
        } catch (NotFoundException e) {
            errorHandler.handleError(e);
        } catch (IOException e) {
            errorHandler.handleError(e);
        }

        return brands;
    }

    private void getBrandsFromServer() {
        GetBrandsUpdater.F.apply(getApplicationContext());
    }

    private void saveVersionCode() {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            PreferenceManager.getDefaultSharedPreferences(this).edit().putInt(BankFinderGlobals.PREFERENCE_VERSION_CODE, packageInfo.versionCode);
        } catch (NameNotFoundException e) {
            errorHandler.handleError(e);
        }
    }

    public static boolean hasSuccesFullAcraInstance() {
        return hasSuccesFullAcraInstance;
    }

    public static ImageViewController getImageViewController() {
        return imageViewController;
    }

    public static DataController getNetworkController() {
        return networkController;
    }

    public static BrandManager getBrandsManager() {
        return brandsManager;
    }

    public static ErrorHandler getErrorHandler() {
        return errorHandler;
    }

}
