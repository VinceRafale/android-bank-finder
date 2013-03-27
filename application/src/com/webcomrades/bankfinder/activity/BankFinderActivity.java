package com.webcomrades.bankfinder.activity;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.analytics.tracking.android.EasyTracker;
import com.webcomrades.bankfinder.BankFinder;
import com.webcomrades.bankfinder.R;

/**
 * @author Jo Somers - sayhello@josomers.be
 * @since 2013
 */

public abstract class BankFinderActivity extends Activity {
	
	protected MenuItem menuLoader;
	protected boolean showRefreshing = false;
	
	@Override
	public void onStart() {
		super.onStart();
		if (BankFinder.inProductionMode()) EasyTracker.getInstance().activityStart(this);
	}

	@Override
	public void onStop() {
		super.onStop();
		if (BankFinder.inProductionMode()) EasyTracker.getInstance().activityStop(this);
	}
	
	protected void initMenuLoader(Menu menu) {
		menuLoader = menu.findItem(R.id.menu_loader);
		if(menuLoader != null) {
			menuLoader.setVisible(showRefreshing);
		}
	}
	
	protected void showSpinnerInActionbar(boolean value) {
		showRefreshing = value;
		if(menuLoader != null) {
			menuLoader.setVisible(value);
		}
	}

}
