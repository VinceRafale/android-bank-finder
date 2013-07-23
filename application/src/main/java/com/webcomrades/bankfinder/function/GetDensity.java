package com.webcomrades.bankfinder.function;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * User: josomers
 * Date: 10/06/13
 * Time: 14:10
 */

public enum GetDensity {

    F;

    public enum Density {
        MDPI, HDPI, XHDPI;
    }

    public Density apply(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();

        if (dm.density == DisplayMetrics.DENSITY_MEDIUM)
            return Density.MDPI;
        else if (dm.density == DisplayMetrics.DENSITY_HIGH)
            return Density.HDPI;
        else if (dm.density == DisplayMetrics.DENSITY_XHIGH)
            return Density.XHDPI;

        return Density.HDPI;
    }
}
