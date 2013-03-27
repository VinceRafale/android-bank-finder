package com.webcomrades.bankfinder.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.webcomrades.bankfinder.BankFinder;
import com.webcomrades.bankfinder.R;
import com.webcomrades.bankfinder.adapter.BankAdapter;
import com.webcomrades.bankfinder.controller.BankManager;
import com.webcomrades.bankfinder.controller.ErrorHandler;
import com.webcomrades.bankfinder.controller.NetworkController;
import com.webcomrades.bankfinder.model.Bank;

/**
 * @author Jo Somers - sayhello@josomers.be
 * @since 2013
 */

public class BankFinderListActivity extends BankFinderActivity {

	private BankAdapter mBankAdapter;
	private ListView mListView;
	private TextView mEmptyView;
	private LinearLayout mLoadingView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banklist);
        
        mBankAdapter = new BankAdapter(this, BankManager.getInstance().getBanks(), BankFinder.getImageViewController());
        
        mListView = (ListView) findViewById(R.id.ListView_Banks);
        mListView.setAdapter(mBankAdapter);
        
        mEmptyView = (TextView) findViewById(R.id.TextView_Empty);
        mEmptyView.setVisibility(View.GONE);
        
        mLoadingView = (LinearLayout) findViewById(R.id.LinearLayout_Loading);
        mLoadingView.setVisibility(View.VISIBLE);
        
        refresh();
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_banklist, menu);
		initMenuLoader(menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.menu_refresh) {
			refresh();
			return true;
		} else if (item.getItemId() == R.id.menu_add) {
			startActivity(new Intent(this, BankFinderAddNewBankActivity.class));
			return true;
		} else {
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
            	List<Bank> mBanks = new ArrayList<Bank>();

                try {
                    mBanks = NetworkController.getBanksFromServer();
                } catch (Exception e) {
                    exception = e;
                }

                return mBanks;
            }

            @Override
            protected void onPostExecute(List<Bank> mBanks) {
                if (exception != null) {
                	ErrorHandler.getInstance().handleError(getApplicationContext(), exception, true);
                }
                
                if (mBanks != null) mBankAdapter.updateBanks(mBanks);
        		mEmptyView.setVisibility(mBankAdapter.isEmpty() ? View.VISIBLE : View.GONE);
        		mLoadingView.setVisibility(View.GONE);
        		showSpinnerInActionbar(false);
            }
        }.execute();
	}
    	
}
