package com.webcomrades.bankfinder.activity;

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

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.webcomrades.bankfinder.BankFinder;
import com.webcomrades.bankfinder.R;
import com.webcomrades.bankfinder.model.Bank;
import com.webcomrades.bankfinder.model.Brand;

import java.util.LinkedList;
import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class NewActivity extends BankFinderActivity {

    private Bank newBank;
    private final List<Brand> brands = new LinkedList<Brand>(BankFinder.getBrandsManager().getBrands().values());

    private final Function<Brand, String> BRAND_NAMES = new Function<Brand, String>() {
        @Override
        public String apply(Brand brand) {
            return brand.getName();
        }
    };

    private Spinner brandSpinner;
    private EditText bankName;
    private EditText bankAddress;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayAdapter<String> brandAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item);
        brandAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        brandAdapter.addAll(Collections2.transform(brands, BRAND_NAMES));

        brandSpinner = (Spinner) findViewById(R.id.Spinner_Brand);
        brandSpinner.setAdapter(brandAdapter);

        bankName = (EditText) findViewById(R.id.EditText_BankName);
        bankAddress = (EditText) findViewById(R.id.EditText_BankAddress);
        submitButton = (Button) findViewById(R.id.Button_Submit);

        submitButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                submitNewBank();
            }
        });
    }

    private void submitNewBank() {
        String name = bankName.getText().toString();
        String address = bankAddress.getText().toString();
        String brandId = brands.get(brandSpinner.getSelectedItemPosition()).getId();

        if (newBank == null) {
            newBank = new Bank(name, address, brandId);
        }

        new AsyncTask<Bank, Void, Bank>() {
            private Exception exception = null;

            @Override
            protected void onPreExecute() {
                showSpinnerInActionbar(true);
                submitButton.setEnabled(false);
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
                    BankFinder.getErrorHandler().showAndHandleError(NewActivity.this, exception);
                } else {
                    Crouton.makeText(NewActivity.this, R.string.tSuccess, Style.CONFIRM).show();
                }

                bankName.setText("");
                bankAddress.setText("");
                submitButton.setEnabled(true);
                showSpinnerInActionbar(false);
            }
        }.execute(newBank);
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