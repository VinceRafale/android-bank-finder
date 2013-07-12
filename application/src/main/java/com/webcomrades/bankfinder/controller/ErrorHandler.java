package com.webcomrades.bankfinder.controller;

import android.app.Activity;

import org.acra.ACRA;

/**
 * @author Jo Somers - sayhello@josomers.be
 * @since 2013
 */

public class ErrorHandler {

    private final boolean inProductionMode;

    public ErrorHandler(boolean inProductionMode) {
        this.inProductionMode = inProductionMode;
    }

    public void showAndHandleError(Activity activity, Throwable throwable) {
        new ErrorDisplayManager(activity).showError(throwable);
        handleError(throwable);
    }

    public void handleError(Throwable throwable) {
        ACRA.getErrorReporter().handleException(throwable);
        if (!inProductionMode) {
            throwable.printStackTrace();
        }
    }

}