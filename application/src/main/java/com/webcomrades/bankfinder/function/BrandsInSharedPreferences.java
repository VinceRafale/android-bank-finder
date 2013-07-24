package com.webcomrades.bankfinder.function;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.webcomrades.bankfinder.controller.DataParser;
import com.webcomrades.bankfinder.model.Brand;

import java.util.List;

/**
 * User: josomers
 * Date: 10/06/13
 * Time: 14:10
 */

public enum BrandsInSharedPreferences {

    F;

    private final String identifier = "brands";

    public List<Brand> get(Context context) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        String data = settings.getString(identifier, null);

        List<Brand> brands = null;
        if (data != null) {
            brands = DataParser.parseBrands(data);
        }

        return brands;
    }

    public void store(Context context, List<Brand> brands) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        settings
                .edit()
                .putString(identifier, new Gson().toJson(brands))
                .commit();
    }
}
