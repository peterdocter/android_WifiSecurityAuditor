package es.rul3s.raul.wifisecurityauditor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TwoLineListItem;

import org.apache.http.message.BasicNameValuePair;

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
        //
    }

    // Create a BroadcastReceiver for ACTION_FOUND
    final BroadcastReceiver wifiScanReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            int size;
            scanResults = wifimgr.getScanResults(); // Returns a <list> of scanResults
            size = scanResults.size();
            tvState.setText("Scan completed, " +size + " wifis found");
            completeListView();
            //Toast.makeText(context, "Scan completed, " +scanResults.size() +" wifis found", Toast.LENGTH_SHORT).show();
        }
    };

    private void checkWifi(){
        if(!wifimgr.isWifiEnabled()){
            tvState.setText(R.string.scan_wifiDisabled);
            //Toast.makeText(this, R.string.scan_wifiDisabled, Toast.LENGTH_SHORT).show();
            wifimgr.setWifiEnabled(true);
        }
    }

    private void scan(){
        if(wifimgr.startScan()){
            tvState.setText(R.string.scan_scanning);
            //Toast.makeText(this, R.string.scan_scanning, Toast.LENGTH_SHORT).show();
        }
        else{
            tvState.setText(R.string.scan_scanningFail);
            //Toast.makeText(this, R.string.scan_scanningFail, Toast.LENGTH_SHORT).show();
        }
    }

    private void completeListView(){
        ScanResult actual;
        final List<String[]> wifiDetails = new LinkedList<String[]>();

        for(int i=0; i<scanResults.size(); i++){
            actual = scanResults.get(i);
            wifiDetails.add(new String[] { actual.SSID, actual.BSSID});
        }

        resultsView.setAdapter(new ArrayAdapter<String[]>(
                this,
                android.R.layout.simple_list_item_2,
                android.R.id.text1,
                wifiDetails) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                // Must always return just a View.
                View view = super.getView(position, convertView, parent);

                // If you look at the android.R.layout.simple_list_item_2 source, you'll see
                // it's a TwoLineListItem with 2 TextViews - text1 and text2.
                //TwoLineListItem listItem = (TwoLineListItem) view;
                String[] entry = wifiDetails.get(position);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                TextView text2 = (TextView) view.findViewById(android.R.id.text2);
                text1.setText(entry[0]);
                text2.setText(entry[1]);
                return view;
            }
        });
    }
}
