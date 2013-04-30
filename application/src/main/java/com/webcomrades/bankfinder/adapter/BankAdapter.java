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
	
	private final Context context;
	private final ImageViewController imageViewController;
	private List<Bank> banks;
	private ViewHolder viewHolder;
	
	public BankAdapter(Context context, ImageViewController imageViewController) {
		this.context = context;
		this.banks = new ArrayList<Bank>();
		this.imageViewController = imageViewController;
	}
	
	public void updateBanks(List<Bank> banks) {
		this.banks = new ArrayList<Bank>(banks);
		notifyDataSetChanged();
	}
		
	@Override
	public int getCount() {
		return banks.size();
	}

	@Override
	public Bank getItem(int position) {
		return banks.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * Use altering background colors for rows.
	 */
	@Override
	public int getItemViewType(int position) {
		return position%2;
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
			convertView = LayoutInflater.from(context).inflate(R.layout.activity_list_item, null);
			viewHolder = new ViewHolder();
			viewHolder.listItem = (LinearLayout) convertView.findViewById(R.id.LinearLayout_ListItem);
			viewHolder.nameTextView = (TextView) convertView.findViewById(R.id.TextView_Name);
			viewHolder.addressTextView = (TextView) convertView.findViewById(R.id.TextView_Address);
			viewHolder.iconImageView = (ImageView) convertView.findViewById(R.id.ImageView_BankIcon);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		switch (viewType) {
		case 0:
			viewHolder.listItem.setBackgroundResource(R.drawable.list_item_background_normal);
			break;
		default:
			viewHolder.listItem.setBackgroundResource(R.drawable.list_item_background_inverse);
		}
				
		viewHolder.nameTextView.setText(bank.getName() != null ? bank.getName() : "");
		viewHolder.addressTextView.setText(bank.getAddress() != null ? bank.getAddress() : "");
		
		viewHolder.iconImageView.setImageResource(R.drawable.ic_defaultbank);
		if (brand != null && brand.getIcon() != null) {
			imageViewController.download(brand.getIcon(), viewHolder.iconImageView, R.drawable.ic_defaultbank);
		}
		
		return convertView;
	}
	
	private class ViewHolder {
		private LinearLayout listItem;
		private TextView nameTextView;
		private TextView addressTextView;
		private ImageView iconImageView;
	}

}
