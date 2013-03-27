package com.webcomrades.bankfinder.controller;

import java.io.File;

import android.app.ActivityManager;
import android.content.Context;
import android.widget.ImageView;

import com.webcomrades.bankfinder.functions.GetUrlForIcon;
import com.webcomrades.bankfinder.model.Icon;
import com.webimageloader.ImageLoader;
import com.webimageloader.ext.ImageHelper;

public class ImageViewController {

	private final ImageLoader mImageLoader;
	private final Context mContext;
	private final String mBaseUrl;

	public ImageViewController(final Context context, final String baseUrl) {
		this.mContext = context;
		this.mBaseUrl = baseUrl;

		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		int memClass = am.getMemoryClass();

		final int memoryCacheSize = 1024 * 1024 * memClass / 8;

		File cacheDir = new File(context.getCacheDir(), "images");
		mImageLoader = new ImageLoader.Builder(context)
				.enableDiskCache(cacheDir, 10 * 1024 * 1024)
				.enableMemoryCache(memoryCacheSize).build();
	}
	
	public void download(Icon icon, ImageView imageView, int defaultPlaceHolder) {
		new ImageHelper(mContext, mImageLoader)
	        .setFadeIn(true)
	        .setLoadingResource(defaultPlaceHolder)
	        .load(imageView, mBaseUrl + GetUrlForIcon.F.apply(icon, mContext));
	}

}
