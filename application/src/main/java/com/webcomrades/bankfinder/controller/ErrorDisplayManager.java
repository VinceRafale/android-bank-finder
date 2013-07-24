package com.webcomrades.bankfinder.controller;

import android.app.Activity;

import com.webcomrades.bankfinder.R;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * User: josomers
 * Date: 10/06/13
 * Time: 14:10
 */

public class ErrorDisplayManager {

    private Activity activity;

    public ErrorDisplayManager(Activity activity) {
        this.activity = activity;
    }

    public void showError(Throwable error) {
        Crouton.makeText(activity, R.string.tError, Style.ALERT).show();
    }

}