package com.webcomrades.bankfinder.function;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.Resources.NotFoundException;

import com.google.common.io.CharStreams;
import com.webcomrades.bankfinder.R;
import com.webcomrades.bankfinder.controller.DataParser;
import com.webcomrades.bankfinder.model.Brand;

public enum GetBrandsFromDevice {

	F;
	
	public List<Brand> apply(Context context) throws NotFoundException, IOException {
		return GetBrandFromRaw(context, R.raw.brand);
	}
	
	private List<Brand> GetBrandFromRaw(Context context, int rawResourceId) throws NotFoundException, IOException {
		String data = CharStreams.toString(new InputStreamReader(context.getResources().openRawResource(rawResourceId), Charset.forName("UTF-8")));
		return (data == null || data.isEmpty()) ? new ArrayList<Brand>() : DataParser.parseBrands(data);
	}
}
