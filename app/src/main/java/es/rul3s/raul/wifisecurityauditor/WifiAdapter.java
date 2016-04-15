package es.rul3s.raul.wifisecurityauditor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

public class WifiAdapter extends ArrayAdapter<WifiDetails> {
    private final Context context;
    private final LinkedList<WifiDetails> wifis;

    public WifiAdapter(Context context, LinkedList<WifiDetails> wifis) {
        super(context, -1, wifis);
        this.context = context;
        this.wifis = wifis;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_wifidetails, parent, false);
        WifiDetails wifi = wifis.get(position);
        ((TextView)rowView.findViewById(R.id.list_bssid)).setText("BSSID: " +wifi.getBssid());
        ((TextView)rowView.findViewById(R.id.list_essid)).setText("ESSID: " +wifi.getEssid());
        ((TextView)rowView.findViewById(R.id.list_channel)).setText("Width: " +wifi.getChannel() +"Mhz - Channel: " +wifi.getChannelNumber());
        ((TextView)rowView.findViewById(R.id.list_security)).setText("Sec: " +wifi.getSecurity());
        return rowView;
    }
}