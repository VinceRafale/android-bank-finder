package com.webcomrades.bankfinder.activity;

import com.google.analytics.tracking.android.EasyTracker;

import android.app.Activity;

/**
 * @author Jo Somers - sayhello@josomers.be
 * @since 2013
 */

public abstract class BankFinderActivity extends Activity {

	@Override
	public void onStart() {
		super.onStart();
		EasyTracker.getInstance().activityStart(this);
	}

	@Override
	public void onStop() {
		super.onStop();
		EasyTracker.getInstance().activityStop(this);
	}

}
