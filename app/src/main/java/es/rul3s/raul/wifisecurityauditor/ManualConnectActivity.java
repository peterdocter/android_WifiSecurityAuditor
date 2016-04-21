package es.rul3s.raul.wifisecurityauditor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ManualConnectActivity extends Activity {
    public static WifiManager wifimgr;
    private TextView tvLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_connect);

        wifimgr = (WifiManager)getSystemService(Context.WIFI_SERVICE);
        tvLog = (TextView) findViewById(R.id.manual_tvLog);

        checkWifi();
    }

    private void checkWifi(){
        if(!wifimgr.isWifiEnabled()){
            Toast.makeText(this,"Wifi disabled, enabling...", Toast.LENGTH_SHORT).show();
            wifimgr.setWifiEnabled(true);
        }
    }

    public void btConnectWps(View view){
        Toast.makeText(this,"Connect by WPS", Toast.LENGTH_SHORT).show();
    }

    public void btConnectPass(View view){
        Toast.makeText(this,"Connect by PASS", Toast.LENGTH_SHORT).show();
        String networkSSID = "";
        String networkBSSID = "";
        String networkPass = "";

        WifiConfiguration wifiConf = new WifiConfiguration();
        wifiConf.SSID = "\"" +networkSSID +"\"";
        wifiConf.BSSID = "\"" +networkBSSID +"\"";
        wifiConf.preSharedKey = "\"" +networkPass +"\"";

        int netId = wifimgr.addNetwork(wifiConf);
        wifimgr.disconnect();
        wifimgr.enableNetwork(netId, true);
        wifimgr.reconnect();
    }

    public void btConnectAdp(View view){
        Toast.makeText(this,"Connect by FIXED", Toast.LENGTH_SHORT).show();
        String networkSSID = "ADP Informatica";
        //String networkBSSID = "64:70:02:6d:c9:c6";
        String networkPass = "academia";

        WifiConfiguration wifiConf = new WifiConfiguration();
        wifiConf.SSID = "\"" +networkSSID +"\"";
        //wifiConf.BSSID = "\"" +networkBSSID +"\"";
        wifiConf.preSharedKey = String.format("\"%s\"",networkPass);
        tvLog.append("SSID: " +wifiConf.SSID +"\n");
        tvLog.append("PASS: " +wifiConf.SSID +"\n");

        int netId = wifimgr.addNetwork(wifiConf);
        tvLog.append("NETID: " +netId +"\n");
        wifimgr.disconnect();
        boolean enableNet = wifimgr.enableNetwork(netId, true);
        tvLog.append("ENABLENETWORK: " +enableNet +"\n");
        wifimgr.reconnect();
        Toast.makeText(this,"Punto3", Toast.LENGTH_SHORT).show();
    }
}
