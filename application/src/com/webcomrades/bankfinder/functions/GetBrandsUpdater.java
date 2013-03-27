package com.webcomrades.bankfinder.functions;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.AsyncTask;

import com.webcomrades.bankfinder.BankFinder;
import com.webcomrades.bankfinder.controller.ErrorHandler;
import com.webcomrades.bankfinder.controller.NetworkController;
import com.webcomrades.bankfinder.model.Brand;

public enum GetBrandsUpdater {

	F;
	
	public void apply(final Context context) {
        new AsyncTask<Void, Void, List<Brand>>() {
            private Exception exception = null;

            @Override
            protected List<Brand> doInBackground(Void... params) {
            	List<Brand> mBrands = new ArrayList<Brand>();

                try {
                    mBrands = NetworkController.getBrandsFromServer();
                } catch (Exception e) {
                    exception = e;
                }

                return mBrands;
            }

            @Override
            protected void onPostExecute(List<Brand> mBrands) {
                if (exception != null) {
                	ErrorHandler.getInstance().handleError(context, exception, false);
                }
                
                if (mBrands != null) BankFinder.getBrandsManager().setBrands(mBrands);
            }
        }.execute();
	}
}
