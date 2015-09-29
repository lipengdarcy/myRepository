package com.example.myfirstapp;

import android.app.Application;
import android.content.Context;

public class MainApplication extends Application{
	 private static Context mContext;

	public static Context getmContext() {
		return mContext;
	}

	public static void setmContext(Context mContext) {
		MainApplication.mContext = mContext;
	}  
	 

}
