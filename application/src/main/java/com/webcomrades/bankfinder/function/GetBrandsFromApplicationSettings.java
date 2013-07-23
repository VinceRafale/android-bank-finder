package com.webcomrades.bankfinder.function;

import android.content.Context;
import android.content.res.Resources.NotFoundException;

import com.webcomrades.bankfinder.model.Brand;

import java.io.IOException;
import java.util.List;

/**
 * User: josomers
 * Date: 10/06/13
 * Time: 14:10
 */

public enum GetBrandsFromApplicationSettings {

    F;

    public List<Brand> apply(Context context) throws NotFoundException, IOException {
        List<Brand> brands = BrandsInSharedPreferences.F.get(context);
        if (brands == null) {
            brands = GetBrandsFromDevice.F.apply(context);
        }
        return brands;
    }
}
