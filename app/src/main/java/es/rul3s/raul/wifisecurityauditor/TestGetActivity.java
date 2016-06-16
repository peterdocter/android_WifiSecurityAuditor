package es.rul3s.raul.wifisecurityauditor;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class TestGetActivity extends Activity {
    String urlString;
    TextView tvResp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_get);
        tvResp = (TextView) findViewById(R.id.json_tvResponse);
        EditText etUrl = (EditText)findViewById(R.id.json_etUrl);
        urlString = etUrl.getText().toString();
    }

    public void testGet(View view){
        tvResp.setText("Testing connectivity...");
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            tvResp.setText(tvResp.getText() +"OK");
            Connectivity con = new Connectivity(this,urlString);
        }else {
            tvResp.setText(tvResp.getText() +"FAIL");
        }
    }
}
