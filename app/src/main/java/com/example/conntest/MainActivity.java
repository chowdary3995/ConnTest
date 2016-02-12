package com.example.conntest;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	public static final int PORT = 8080;
	public String IP_Address = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView textView = (TextView)findViewById(R.id.textView1);
		WifiManager wim= (WifiManager) getSystemService(WIFI_SERVICE);
		if(wim != null){
			IP_Address = Formatter.formatIpAddress(wim.getConnectionInfo().getIpAddress());
		textView.append("\n"+ IP_Address);
		}
		checkWifi();
	
	}
	
	private void checkWifi(){      
	     IntentFilter filter = new IntentFilter();
	     filter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
	     final WifiManager wifiManager = 
	                         (WifiManager)getSystemService(Context.WIFI_SERVICE);;
	                          registerReceiver(new BroadcastReceiver(){
	        @Override
	        public void onReceive(Context arg0, Intent arg1) {

	            String scanList = wifiManager.getScanResults().toString();
	            System.out.println("Scan:"+scanList);
	        }           
	      },filter);        
	        wifiManager.startScan();
	      }
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	

}
