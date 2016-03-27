package es.rul3s.raul.wifisecurityauditor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ScanActivity extends AppCompatActivity {
    public static WifiManager wifimgr;
    public List<ScanResult> scanResults;

    private Context context;
    private TextView tvState;
    private ListView resultsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        tvState = (TextView)findViewById(R.id.scan_tvState);
        resultsView = (ListView)findViewById(R.id.scan_lvResults);
        wifimgr = (WifiManager)getSystemService(Context.WIFI_SERVICE);
        context = this;

        // Register the BroadcastReceiver
        IntentFilter wifiScanFilter = new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        registerReceiver(wifiScanReceiver, wifiScanFilter); // Don't forget to unregister during onDestroy

        checkWifi(wifimgr);
        scan(wifimgr);

    }

    // Create a BroadcastReceiver for ACTION_FOUND
    final BroadcastReceiver wifiScanReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            int size;
            scanResults = wifimgr.getScanResults(); // Returns a <list> of scanResults
            size = scanResults.size();
            tvState.setText("Scan completed, " +size + " wifis found");
            //Toast.makeText(context, "Scan completed, " +scanResults.size() +" wifis found", Toast.LENGTH_SHORT).show();
        }
    };

    private void checkWifi(WifiManager wifimgr){
        if(!wifimgr.isWifiEnabled()){
            tvState.setText(R.string.scan_wifiDisabled);
            //Toast.makeText(this, R.string.scan_wifiDisabled, Toast.LENGTH_SHORT).show();
            wifimgr.setWifiEnabled(true);
        }
    }

    private void scan(WifiManager wifimgr){
        if(wifimgr.startScan()){
            tvState.setText(R.string.scan_scanning);
            //Toast.makeText(this, R.string.scan_scanning, Toast.LENGTH_SHORT).show();
        }
        else{
            tvState.setText(R.string.scan_scanningFail);
            //Toast.makeText(this, R.string.scan_scanningFail, Toast.LENGTH_SHORT).show();
        }
    }
}


/*
        IntentFilter i = new IntentFilter();
        i.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        registerReceiver(new BroadcastReceiver(){
            public void onReceive(Context c, Intent i){
                // Code to execute when SCAN_RESULTS_AVAILABLE_ACTION event occurs
                WifiManager w = (WifiManager) c.getSystemService(Context.WIFI_SERVICE);
                scanResults = w.getScanResults(); // Returns a <list> of scanResults
            }
        }, i );

        // Now you can call this and it should execute the broadcastReceiver's onReceive()
        WifiManager wm = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        boolean a = wm.startScan();
        */
