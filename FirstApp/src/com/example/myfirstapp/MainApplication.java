package com.example.myfirstapp;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.wifi.p2p.WifiP2pManager;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.LruCache;

/*
 Application和Activity,Service一样是android框架的一个系统组件，
 当android程序启动时系统会创建一个 application对象，用来存储系统的一些信息。
 通常我们是不需要指定一个Application的，这时系统会自动帮我们创建，
 如果需要创建自己 的Application，也很简单创建一个类继承 Application并在
 manifest的application标签中进行注册(只需要给Application标签增加个name属性
 把自己的 Application的名字定入即可)。 
 */
public class MainApplication extends Application {
	private static Context mContext;

	/*
	 * 第一步、写一个全局的单例模式的MyApplication继承自Application
	 * 覆盖onCreate，在这个方法里面实例化Application 
	 * 第二步、配置全局的Context <application
	 * android:name="com.example.utils.myapplication" android:allowBackup="true"
	 * android:icon="@drawable/ic_launcher" android:label="@string/app_name"
	 * android:theme="@style/AppTheme" > </application>
	 * 第三步、使用，使用的时候用的时候根据类的名称访问Context
	 */

	/**
	 * 为了完全退出程序调用方法 MainApplication.getInstance().addActivity(this);
	 * myapplication1.getInstance().exit();
	 */
	private static MainApplication instance;

	private List<Activity> activityList = new LinkedList<Activity>();

	public MainApplication() {
	}

	// 单例模式获取唯一的MainApplication实例
	public static MainApplication getInstance() {
		if (null == instance) {
			instance = new MainApplication();
		}
		return instance;
	}

	// 添加Activity到容器中
	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	// 遍历所有Activity并finish
	public void exit() {
		for (Activity activity : activityList) {
			activity.finish();
		}
		System.exit(0);
	}

	public static Context getmContext() {
		return mContext;
	}

	public static void setmContext(Context mContext) {
		MainApplication.mContext = mContext;
	}

	@Override
	public void onCreate() {

		super.onCreate();
		// 初始化全局变量
		try {
			/**
			 * 添加网络权限，安卓4.03必须
			 */
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
					.detectDiskReads().detectDiskWrites().detectNetwork()
					.penaltyLog().build());
			StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
					.detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
					.penaltyLog().penaltyDeath().build());
			/**
			 * 添加网络权限，安卓4.03必须
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
