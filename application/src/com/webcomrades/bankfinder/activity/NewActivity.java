package com.webcomrades.bankfinder.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.analytics.tracking.android.Log;
import com.webcomrades.bankfinder.R;
import com.webcomrades.bankfinder.controller.ErrorHandler;
import com.webcomrades.bankfinder.controller.NetworkController;
import com.webcomrades.bankfinder.model.Bank;

public class NewActivity extends BankFinderActivity {

	private Bank mNewBank;
	
	private Spinner mBrandSpinner;
	private EditText mBankName;
	private EditText mBankAddress;
	private Button mSubmitButton;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        
        mNewBank = new Bank();
        
        Log.w("new bank id: " + mNewBank.id); //TODO remove this!
        
        mBrandSpinner = (Spinner) findViewById(R.id.Spinner_Brand);
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
    
	private void submitNewBank() {
		String name = mBankName.getText().toString();
		String address = mBankAddress.getText().toString();
		
		mNewBank.name = (!name.isEmpty()) ? name : "";
		mNewBank.address = (!address.isEmpty()) ? address : "";

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
                	mBank = NetworkController.postBankToServer(mNewBank[0]);
                } catch (Exception e) {
                    exception = e;
                }

                return mBank;
            }

            @Override
            protected void onPostExecute(Bank mBank) {
                if (exception != null) {
                	ErrorHandler.getInstance().handleError(getApplicationContext(), exception, true);
                }
                
                if (mBank != null) {
                	Log.w("yeeha " + mBank.id);
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
		if (item.getItemId() == R.id.menu_add) {
			startActivity(new Intent(this, NewActivity.class));
			return true;
		} else {
			return super.onOptionsItemSelected(item);
		}
	}
    
}