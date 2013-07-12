package com.webcomrades.bankfinder.function;

import android.content.Context;
import android.os.AsyncTask;

import com.google.common.collect.Lists;
import com.webcomrades.bankfinder.BankFinder;
import com.webcomrades.bankfinder.model.Brand;

import java.util.List;

/**
 * @author Jo Somers - sayhello@josomers.be
 * @since 2013
 */

public enum GetBrandsUpdater {

    F;

    public void apply(final Context context) {
        new AsyncTask<Void, Void, List<Brand>>() {
            private Exception exception = null;

            @Override
            protected List<Brand> doInBackground(Void... params) {
                List<Brand> brands = Lists.newArrayList();

                try {
                    brands = BankFinder.getNetworkController().getBrands();
                } catch (Exception e) {
                    exception = e;
                }

                return brands;
            }

            @Override
            protected void onPostExecute(List<Brand> brands) {
                if (exception != null) {
                    BankFinder.getErrorHandler().handleError(exception);
                } else {
                    BankFinder.getBrandsManager().setBrands(brands);
                    BrandsInSharedPreferences.F.store(context, Lists.newArrayList(brands));
                }
            }
        }.execute();
    }
}
