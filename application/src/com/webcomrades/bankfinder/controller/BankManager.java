package com.webcomrades.bankfinder.controller;

import java.util.ArrayList;
import java.util.List;

import com.webcomrades.bankfinder.model.Bank;

/**
 * @author Jo Somers - sayhello@josomers.be
 * @since 2013
 */

public class BankManager {

	private List<Bank> mBanks;
	private static BankManager mBankController;
	
	private BankManager() {
		mBanks = new ArrayList<Bank>();
	}
	
	public static BankManager getInstance() {
		return (mBankController != null) ? mBankController : new BankManager();
	}
	
	public List<Bank> getBanks() {
		return mBanks;
	}
	
	public void setBanks(List<Bank> banks) {
		this.mBanks = banks;
	}
	
}
