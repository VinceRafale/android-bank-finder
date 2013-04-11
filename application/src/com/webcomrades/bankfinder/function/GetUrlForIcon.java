package com.webcomrades.bankfinder.function;

import android.content.Context;

import com.webcomrades.bankfinder.model.Icon;

/**
 * @author Jo Somers - sayhello@josomers.be
 * @since 2013
 */

public enum GetUrlForIcon {
	
	F;
	
	public String apply(Icon icon, Context context) {
		String url = icon.getUrl();
		
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
		
		url += icon.getFileName();
		
		return url;
	}

}
