package com.webcomrades.bankfinder.functions;

import android.content.Context;

import com.webcomrades.bankfinder.model.Icon;

public enum GetUrlForIcon {
	
	F;
	
	public String apply(Icon icon, Context context) {
		String url = icon.url;
		
		switch (GetDensity.F.apply(context)) {
		case MDPI:
			url += "mdpi/";
			break;
		case HDPI:
			url += "hdpi/";
			break;
		case XHDPI:
			url += "xhdpi/";
			break;
		default:
			break;
		}
		
		url += icon.fileName;
		
		return url;
	}

}
