package com.webcomrades.bankfinder.controller;

import android.app.Activity;

import com.webcomrades.bankfinder.BankFinder;

import org.acra.ACRA;

/**
 * User: josomers
 * Date: 10/06/13
 * Time: 14:10
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
        if (BankFinder.hasSuccesFullAcraInstance()) {
            ACRA.getErrorReporter().handleException(throwable);
        }

        if (!inProductionMode) {
            throwable.printStackTrace();
        }
    }

}