package com.webcomrades.bankfinder.activity;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.analytics.tracking.android.EasyTracker;
import com.webcomrades.bankfinder.BankFinderGlobals;
import com.webcomrades.bankfinder.R;

import de.keyboardsurfer.android.widget.crouton.Crouton;

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
		if (BankFinderGlobals.inProductionMode()) EasyTracker.getInstance().activityStart(this);
	}

	@Override
	public void onStop() {
		super.onStop();
		if (BankFinderGlobals.inProductionMode()) EasyTracker.getInstance().activityStop(this);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Crouton.cancelAllCroutons(); // it's important to dismiss all croutons when destroying the activity!
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
