package com.webcomrades.bankfinder.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.webcomrades.bankfinder.BankFinder;
import com.webcomrades.bankfinder.R;
import com.webcomrades.bankfinder.model.Bank;
import com.webcomrades.bankfinder.model.Brand;

public class DetailActivity extends BankFinderActivity {

	private Bank mBank;

	private TextView mBrandNameTextView;
	private TextView mNameTextView;
	private TextView mAddressTextView;
	private TextView mNoDetailTextView;
	private ImageView mIconImageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		// get intent extra's
		Intent intent = getIntent();
		if (intent != null && intent.getStringExtra("bank") != null) {
			String bankDetail = intent.getStringExtra("bank");
			mBank = new Gson().fromJson(bankDetail, Bank.class);
		}
		
		mNoDetailTextView = (TextView) findViewById(R.id.TextView_NoDetailInfo);
		mBrandNameTextView = (TextView) findViewById(R.id.TextView_Brand);
		mNameTextView = (TextView) findViewById(R.id.TextView_Name);
		mAddressTextView = (TextView) findViewById(R.id.TextView_Address);
		mIconImageView = (ImageView) findViewById(R.id.ImageView_BankIcon);
		
		setBankDetailInformation();
		
		mIconImageView.setVisibility(mBank != null && mBank.getBrand() != null 
				&& mBank.getBrand().icon != null ? View.VISIBLE : View.GONE);
	}
	
	private void setBankDetailInformation() {
		if (mBank != null) {
			if (mBank.name != null) {
				mNameTextView.setText(mBank.name);
				mNameTextView.setVisibility(View.VISIBLE);
			} else {
				mNameTextView.setVisibility(View.GONE);
			}
			
			if (mBank.address != null) {
				mAddressTextView.setText(mBank.address);
				mAddressTextView.setVisibility(View.VISIBLE);
			} else {
				mAddressTextView.setVisibility(View.GONE);
			}
			
			final Brand brand = mBank.getBrand();
			
			mIconImageView.setImageResource(R.drawable.ic_defaultbank);
			if (brand != null && brand.icon != null) {
				BankFinder.getImageViewController().download(brand.icon, mIconImageView, R.drawable.ic_defaultbank);
			}
			
			if (brand != null && brand.name != null) {
				mBrandNameTextView.setText(brand.name);
				mBrandNameTextView.setVisibility(View.VISIBLE);
			} else {
				mBrandNameTextView.setVisibility(View.GONE);
			}
						
			mNoDetailTextView.setVisibility(View.GONE);
		} else {
			mNoDetailTextView.setVisibility(View.VISIBLE);
		}
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}