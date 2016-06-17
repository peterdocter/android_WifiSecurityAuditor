package es.rul3s.raul.wifisecurityauditor;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class ScanActivity extends AppCompatActivity {
    public static WifiManager wifimgr;
    public List<ScanResult> scanResults;
    public LinkedList<WifiDetails> wifiList = new LinkedList<>();

    private Context context;
    private Activity activity;
    private TextView tvState;
    private ListView resultsView;

    private int selected = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        tvState = (TextView)findViewById(R.id.scan_tvState);
        resultsView = (ListView)findViewById(R.id.scan_lvResults);
        wifimgr = (WifiManager)getSystemService(Context.WIFI_SERVICE);
        context = this;
        activity = this;

        resultsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context,"Click " +position,Toast.LENGTH_SHORT).show();
                selected = position;
                activity.openContextMenu(parent);
            }
        });

        // Register the BroadcastReceiver
        IntentFilter wifiScanFilter = new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
        registerReceiver(wifiScanReceiver, wifiScanFilter); // Don't forget to unregister during onDestroy

        registerForContextMenu(resultsView);
        checkWifi();
        scan();
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.onclick_listview, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // below variable info contains clicked item info and it can be null; scroll down to see a fix for it
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()){
            case R.id.onclick_lvInsert:
                callInsertActivity();
                return true;
            case R.id.onclick_lvVulnerabilities:
                callVulnerabilitiesActivity();
                //Toast.makeText(this,"VULNERABILITIES",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void callInsertActivity(){
        WifiDetails wifi = wifiList.get(selected);

        Toast.makeText(this,"INSERT",Toast.LENGTH_SHORT).show();
        Intent insertData = new Intent(this,SearchInsertActivity.class);
        insertData.putExtra("action","insert");
        insertData.putExtra("bssid",wifi.getBssid());
        insertData.putExtra("essid",wifi.getEssid());
        insertData.putExtra("channel",wifi.getChannel());
        insertData.putExtra("security",wifi.getSecurity());
        startActivity(insertData);
    }

    private void callVulnerabilitiesActivity(){
        WifiDetails wifi = wifiList.get(selected);

        //Toast.makeText(this,"VULNERABILITIES",Toast.LENGTH_SHORT).show();
        Intent vulnerabilities = new Intent(this,VulnerabilitiesActivity.class);
        vulnerabilities.putExtra("security",wifi.getSecurity());
        startActivity(vulnerabilities);
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

        for(int i=0; i<scanResults.size(); i++){
            actual = scanResults.get(i);
            wifiList.add(new WifiDetails(actual.BSSID,actual.SSID,actual.capabilities,actual.frequency));
        }

        WifiAdapter listAdapter = new WifiAdapter(this, wifiList);
        resultsView.setAdapter(listAdapter);
    }
}
