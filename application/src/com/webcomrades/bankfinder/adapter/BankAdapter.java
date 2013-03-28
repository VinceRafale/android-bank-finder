package com.webcomrades.bankfinder.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.webcomrades.bankfinder.R;
import com.webcomrades.bankfinder.controller.ImageViewController;
import com.webcomrades.bankfinder.model.Bank;
import com.webcomrades.bankfinder.model.Brand;

/**
 * @author Jo Somers - sayhello@josomers.be
 * @since 2013
 */

public class BankAdapter extends BaseAdapter {
	
	private final static int EVEN = 0;
	private final static int UNEVEN = 1;
	
	private final Context mContext;
	private final ImageViewController mImageViewController;
	private List<Bank> mBanks;
	private ViewHolder mViewHolder;
	
	public BankAdapter(Context context, List<Bank> banks, ImageViewController imageViewController) {
		this.mContext = context;
		this.mBanks = new ArrayList<Bank>(banks);
		this.mImageViewController = imageViewController;
	}
	
	public void updateBanks(List<Bank> banks) {
		this.mBanks = new ArrayList<Bank>(banks);
		notifyDataSetChanged();
	}
	
	@Override
	public int getCount() {
		return mBanks.size();
	}

	@Override
	public Bank getItem(int position) {
		return mBanks.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public int getItemViewType(int position) {
		return position%2 == 0 ? EVEN : UNEVEN;
	}

	@Override
	public int getViewTypeCount() {
		return 2;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final Bank bank = getItem(position);
		final Brand brand = bank.getBrand();
		final int viewType = getItemViewType(position);
		
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_list_item, null);
			mViewHolder = new ViewHolder();
			mViewHolder.mListItem = (LinearLayout) convertView.findViewById(R.id.LinearLayout_ListItem);
			mViewHolder.mNameTextView = (TextView) convertView.findViewById(R.id.TextView_Name);
			mViewHolder.mAddressTextView = (TextView) convertView.findViewById(R.id.TextView_Address);
			mViewHolder.mIconImageView = (ImageView) convertView.findViewById(R.id.ImageView_BankIcon);
			convertView.setTag(mViewHolder);
		} else {
			mViewHolder = (ViewHolder) convertView.getTag();
		}

		switch (viewType) {
		case EVEN:
			mViewHolder.mListItem.setBackground(mContext.getResources().getDrawable(R.drawable.list_item_background_normal));
			break;
		default:
			mViewHolder.mListItem.setBackground(mContext.getResources().getDrawable(R.drawable.list_item_background_inverse));
			break;
		}
				
		mViewHolder.mNameTextView.setText(bank.name != null ? bank.name : "");
		mViewHolder.mAddressTextView.setText(bank.address != null ? bank.address : "");
		
		mViewHolder.mIconImageView.setImageResource(R.drawable.ic_defaultbank);
		if (brand != null && brand.icon != null) {
			mImageViewController.download(brand.icon, mViewHolder.mIconImageView, R.drawable.ic_defaultbank);
		}
		
		return convertView;
	}
	
	private class ViewHolder {
		private LinearLayout mListItem;
		private TextView mNameTextView;
		private TextView mAddressTextView;
		private ImageView mIconImageView;
	}

}
