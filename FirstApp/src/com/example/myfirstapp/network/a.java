package com.example.myfirstapp.network;

import android.content.Context;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdManager.RegistrationListener;
import android.net.nsd.NsdServiceInfo;


public class a {
	
	RegistrationListener mRegistrationListener;
	
	String mServiceName;
	
	
	//注册NSD服务
	/**
	 * 这段代码将所提供的服务命名为“NsdChat”。该名称将对所有局域网络中的设备可见。 需要注意的是，在网络内该名称必须是独一无二的。
	 * Android系统会自动处理冲突的服务名称。如果同时有两个名为“NsdChat”的应用， 其中一个会被自动转换为“NsdChat(1)”。

第二个参数设置了服务类型，即，使用的通信协议和传输层协议， 语法是“_< protocol >._< transportlayer >”。 在上面的代码中，
服务使用了TCP协议上的HTTP协议。 如果应用想要提供打印服务（例如，一台网络打印机）应该将服务的类型设置为 “_ipp._tcp”。
	 * */
	public void registerService(int port) {
	    // Create the NsdServiceInfo object, and populate it.
	    NsdServiceInfo serviceInfo  = new NsdServiceInfo();

	    // The name is subject to change based on conflicts
	    // with other services advertised on the same network.
	    serviceInfo.setServiceName("NsdChat");
	    serviceInfo.setServiceType("_http._tcp.");
	    serviceInfo.setPort(port);
	    
	    
	   // mNsdManager = Context.getSystemService(Context.NSD_SERVICE);

	   // mNsdManager.registerService(
	          //  serviceInfo, NsdManager.PROTOCOL_DNS_SD, mRegistrationListener);
	    
	}
	
	public void initializeRegistrationListener() {
	    mRegistrationListener = new NsdManager.RegistrationListener() {

	        //@Override
	        public void onServiceRegistered(NsdServiceInfo NsdServiceInfo) {
	            // Save the service name.  Android may have changed it in order to
	            // resolve a conflict, so update the name you initially requested
	            // with the name Android actually used.
	            mServiceName = NsdServiceInfo.getServiceName();
	        }

	        //@Override
	        public void onRegistrationFailed(NsdServiceInfo serviceInfo, int errorCode) {
	            // Registration failed!  Put debugging code here to determine why.
	        }

	        //@Override
	        public void onServiceUnregistered(NsdServiceInfo arg0) {
	            // Service has been unregistered.  This only happens when you call
	            // NsdManager.unregisterService() and pass in this listener.
	        }

	        //@Override
	        public void onUnregistrationFailed(NsdServiceInfo serviceInfo, int errorCode) {
	            // Unregistration failed.  Put debugging code here to determine why.
	        }
	    };
	}
	
	

}
