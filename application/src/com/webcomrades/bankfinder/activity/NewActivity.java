package com.webcomrades.bankfinder.activity;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.webcomrades.bankfinder.BankFinder;
import com.webcomrades.bankfinder.R;
import com.webcomrades.bankfinder.controller.ErrorHandler;
import com.webcomrades.bankfinder.model.Bank;
import com.webcomrades.bankfinder.model.Brand;

public class NewActivity extends BankFinderActivity {

	private Bank mNewBank;
	private final List<Brand> mBrands = new LinkedList<Brand>(BankFinder.getBrandsManager().getBrands().values());
	
	private Spinner mBrandSpinner;
	private EditText mBankName;
	private EditText mBankAddress;
	private Button mSubmitButton;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        
		ArrayAdapter<String> brandAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item);
		brandAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		brandAdapter.addAll(getBrandNames());
        
        mBrandSpinner = (Spinner) findViewById(R.id.Spinner_Brand);
        mBrandSpinner.setAdapter(brandAdapter);
        
        mBankName = (EditText) findViewById(R.id.EditText_BankName);
        mBankAddress = (EditText) findViewById(R.id.EditText_BankAddress);
        mSubmitButton = (Button) findViewById(R.id.Button_Submit);
        
        mSubmitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				submitNewBank();
			}
		});
    }
    
    private Collection<String> getBrandNames() {
		Function<Brand, String> transFormBrands = new Function<Brand, String>() {
			@Override
			public String apply(Brand brand) {
				return brand.name;
			}
		};
        
		return Collections2.transform(mBrands, transFormBrands);
    }
    
	private void submitNewBank() {
		String name = mBankName.getText().toString();
		String address = mBankAddress.getText().toString();
		String brandId = mBrands.get(mBrandSpinner.getSelectedItemPosition()).id;
		
		if (mNewBank == null) {
			mNewBank = new Bank(name, address, brandId);
		}

        new AsyncTask<Bank, Void, Bank>() {
            private Exception exception = null;

            @Override
            protected void onPreExecute() {
                showSpinnerInActionbar(true);
                mSubmitButton.setEnabled(false);
                super.onPreExecute();
            }

            @Override
            protected Bank doInBackground(Bank... mNewBank) {
            	Bank mBank = null;

                try {
                	mBank = BankFinder.getNetworkController().postBank(mNewBank[0]);
                } catch (Exception e) {
                    exception = e;
                }

                return mBank;
            }

            @Override
            protected void onPostExecute(Bank mBank) {
                if (exception != null) {
                	ErrorHandler.getInstance().handleError(getApplicationContext(), exception, true);
                } else {
                	Toast.makeText(getApplicationContext(), getString(R.string.tSuccess), Toast.LENGTH_SHORT).show();
                	finish();
                }
                
        		mSubmitButton.setEnabled(true);
        		showSpinnerInActionbar(false);
            }
        }.execute(mNewBank);
	}
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_new, menu);
		initMenuLoader(menu);
		return super.onCreateOptionsMenu(menu);
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