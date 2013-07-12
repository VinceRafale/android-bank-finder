package com.webcomrades.bankfinder.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.webcomrades.bankfinder.BankFinder;
import com.webcomrades.bankfinder.R;
import com.webcomrades.bankfinder.adapter.BankAdapter;
import com.webcomrades.bankfinder.model.Bank;

import java.util.List;

/**
 * @author Jo Somers - sayhello@josomers.be
 * @since 2013
 */

public class ListActivity extends BankFinderActivity implements OnItemClickListener {

    private BankAdapter bankAdapter;
    private ListView listView;
    private TextView emptyView;
    private ProgressBar loadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        bankAdapter = new BankAdapter(this, BankFinder.getImageViewController());

        listView = (ListView) findViewById(R.id.ListView_Banks);
        listView.setAdapter(bankAdapter);
        listView.setOnItemClickListener(this);

        emptyView = (TextView) findViewById(R.id.TextView_Empty);
        emptyView.setVisibility(View.GONE);

        loadingView = (ProgressBar) findViewById(R.id.Progressbar_Loading);
        loadingView.setVisibility(View.VISIBLE);

        refresh();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list, menu);
        initMenuLoader(menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refresh:
                refresh();
                return true;
            case R.id.menu_add:
                startActivity(new Intent(this, NewActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void refresh() {
        new AsyncTask<Void, Void, List<Bank>>() {
            private Exception exception = null;

            @Override
            protected void onPreExecute() {
                showSpinnerInActionbar(true);
                super.onPreExecute();
            }

            @Override
            protected List<Bank> doInBackground(Void... params) {
                List<Bank> banks = Lists.newArrayList();

                try {
                    banks = BankFinder.getNetworkController().getBanks();
                } catch (Exception e) {
                    exception = e;
                }

                return banks;
            }

            @Override
            protected void onPostExecute(List<Bank> banks) {
                if (exception != null) {
                    BankFinder.getErrorHandler().showAndHandleError(ListActivity.this, exception);
                } else {
                    bankAdapter.updateBanks(banks);
                }

                emptyView.setVisibility(bankAdapter.isEmpty() ? View.VISIBLE : View.GONE);
                loadingView.setVisibility(View.GONE);
                showSpinnerInActionbar(false);
            }
        }.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent bankDetailIntent = new Intent(this, DetailActivity.class);
        bankDetailIntent.putExtra("bank", new Gson().toJson(bankAdapter.getItem(position)));
        startActivity(bankDetailIntent);
    }

}
