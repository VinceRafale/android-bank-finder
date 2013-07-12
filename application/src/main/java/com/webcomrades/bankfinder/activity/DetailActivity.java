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

/**
 * @author Jo Somers - sayhello@josomers.be
 * @since 2013
 */

public class DetailActivity extends BankFinderActivity {

    private TextView brandNameTextView;
    private TextView nameTextView;
    private TextView addressTextView;
    private TextView noDetailTextView;
    private ImageView iconImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        // get intent extra's
        Intent intent = getIntent();
        Bank bank = null;
        if (intent != null && intent.getStringExtra("bank") != null) {
            String bankDetail = intent.getStringExtra("bank");
            bank = new Gson().fromJson(bankDetail, Bank.class);
        }

        noDetailTextView = (TextView) findViewById(R.id.TextView_NoDetailInfo);
        brandNameTextView = (TextView) findViewById(R.id.TextView_Brand);
        nameTextView = (TextView) findViewById(R.id.TextView_Name);
        addressTextView = (TextView) findViewById(R.id.TextView_Address);
        iconImageView = (ImageView) findViewById(R.id.ImageView_BankIcon);

        setBankDetailInformation(bank);

        iconImageView.setVisibility(bank != null && bank.getBrand() != null
                && bank.getBrand().getIcon() != null ? View.VISIBLE : View.GONE);
    }

    private void setBankDetailInformation(Bank bank) {
        if (bank != null) {
            if (bank.getName() != null) {
                nameTextView.setText(bank.getName());
                nameTextView.setVisibility(View.VISIBLE);
            } else {
                nameTextView.setVisibility(View.GONE);
            }

            if (bank.getAddress() != null) {
                addressTextView.setText(bank.getAddress());
                addressTextView.setVisibility(View.VISIBLE);
            } else {
                addressTextView.setVisibility(View.GONE);
            }

            final Brand brand = bank.getBrand();

            iconImageView.setImageResource(R.drawable.ic_defaultbank);
            if (brand != null && brand.getIcon() != null) {
                BankFinder.getImageViewController().download(brand.getIcon(), iconImageView, R.drawable.ic_defaultbank);
            }

            if (brand != null && brand.getName() != null) {
                brandNameTextView.setText(brand.getName());
                brandNameTextView.setVisibility(View.VISIBLE);
            } else {
                brandNameTextView.setVisibility(View.GONE);
            }

            noDetailTextView.setVisibility(View.GONE);
        } else {
            noDetailTextView.setVisibility(View.VISIBLE);
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