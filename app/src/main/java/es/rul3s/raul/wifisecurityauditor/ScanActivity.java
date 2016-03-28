package es.rul3s.raul.wifisecurityauditor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;
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

        checkWifi();
        scan();
    }

    // Create a BroadcastReceiver for ACTION_FOUND
    final BroadcastReceiver wifiScanReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            int size;
            scanResults = wifimgr.getScanResults(); // Returns a <list> of scanResults
            size = scanResults.size();
            tvState.setText("Scan completed, " +size + " wifis found");
            completeListView();
        }
    };

    private void checkWifi(){
        if(!wifimgr.isWifiEnabled()){
            tvState.setText(R.string.scan_wifiDisabled);
            wifimgr.setWifiEnabled(true);
        }
    }

    private void scan(){
        if(wifimgr.startScan())tvState.setText(R.string.scan_scanning);
        else tvState.setText(R.string.scan_scanningFail);
    }

    private void completeListView(){
        ScanResult actual;
        LinkedList<WifiDetails> wifiList = new LinkedList<>();

        for(int i=0; i<scanResults.size(); i++){
            actual = scanResults.get(i);
            wifiList.add(new WifiDetails(actual.BSSID,actual.SSID,actual.capabilities,actual.frequency));
        }

        WifiAdapter listAdapter = new WifiAdapter(this, wifiList);
        resultsView.setAdapter(listAdapter);
        /*
        //Create the adapter for the ListView, modified ArrayAdapter to fit twolineslaoyout
        resultsView.setAdapter(new ArrayAdapter<String[]>(
                this, android.R.layout.simple_list_item_2, android.R.id.text1, wifiDetails){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                String[] entry = wifiDetails.get(position);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);
                text1.setText(entry[0]);
                text2.setText(entry[1]);
                return view;
            }
        });
        */
    }
}
