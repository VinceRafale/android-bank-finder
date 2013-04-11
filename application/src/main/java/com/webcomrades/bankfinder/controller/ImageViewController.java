package com.webcomrades.bankfinder.controller;

import java.io.File;

import android.app.ActivityManager;
import android.content.Context;
import android.widget.ImageView;

import com.webcomrades.bankfinder.function.GetUrlForIcon;
import com.webcomrades.bankfinder.model.Icon;
import com.webimageloader.ImageLoader;
import com.webimageloader.ext.ImageHelper;

/**
 * @author Jo Somers - sayhello@josomers.be
 * @since 2013
 */

public class ImageViewController {

	private final ImageLoader imageLoader;
	private final Context context;
	private final String baseUrl;

	public ImageViewController(final Context context, final String baseUrl) {
		this.context = context;
		this.baseUrl = baseUrl;

		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		int memClass = am.getMemoryClass();

		final int memoryCacheSize = 1024 * 1024 * memClass / 8;

		File cacheDir = new File(context.getCacheDir(), "images");
		imageLoader = new ImageLoader.Builder(context)
				.enableDiskCache(cacheDir, 10 * 1024 * 1024)
				.enableMemoryCache(memoryCacheSize).build();
	}
	
	public void download(Icon icon, ImageView imageView, int defaultPlaceHolder) {
		new ImageHelper(context, imageLoader)
	        .setFadeIn(true)
	        .setLoadingResource(defaultPlaceHolder)
	        .load(imageView, baseUrl + GetUrlForIcon.F.apply(icon, context));
	}

}
