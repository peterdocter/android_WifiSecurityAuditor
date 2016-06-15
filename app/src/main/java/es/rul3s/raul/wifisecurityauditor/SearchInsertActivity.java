package es.rul3s.raul.wifisecurityauditor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONObject;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class SearchInsertActivity extends Activity {
    private EditText etBSSID;
    private EditText etESSID;
    private EditText etSecurity;
    private EditText etMake;
    private EditText etModel;
    private EditText etCompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_insert);

        Intent intent = getIntent();
        Button btInsert = (Button) findViewById(R.id.searchinsert_btInsert);
        Button btSearch = (Button) findViewById(R.id.searchinsert_btSearch);
        etBSSID = (EditText) findViewById(R.id.searchinsert_etBSSID);
        etESSID = (EditText) findViewById(R.id.searchinsert_etESSID);
        etSecurity = (EditText) findViewById(R.id.searchinsert_etSecurity);
        etMake = (EditText) findViewById(R.id.searchinsert_etMake);
        etModel = (EditText) findViewById(R.id.searchinsert_etModel);
        etCompany = (EditText) findViewById(R.id.searchinsert_etCompany);

        String action = (String) intent.getStringExtra("action").toString();

        if(action.equals("insert")){
            btSearch.setEnabled(false);
            fillData(intent);
        }else{
            btInsert.setEnabled(false);
        }
    }

    private void fillData(Intent intent){
        etBSSID.setText(intent.getStringExtra("bssid"));
        etESSID.setText(intent.getStringExtra("essid"));
        etSecurity.setText(intent.getStringExtra("security"));
    }

    public void insertToDb(View v){
        postData();
    }

    private boolean postData() {
        String json;

        json = "{\n" +
                "'bssid':'" +etBSSID.getText().toString() +"',\n" +
                "'essid':'" +etESSID.getText().toString() +"',\n" +
                "'security':'" +etSecurity.getText().toString() +"',\n" +
                "'make':'" +etMake.getText().toString() +"',\n" +
                "'model':'" +etModel.getText().toString() +"',\n" +
                "'company':'" +etCompany.getText().toString() +"'\n" +
                "}";

        Toast.makeText(this, json, Toast.LENGTH_LONG).show();

        return true;
    }

    /*
    public static String POST(WifiDetails wifi){
        InputStream inputStream = null;
        String result = "";
        try{
            URL url=new URL("http://db.rul3s.es");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            JSONObject dataJson = new JSONObject();
            dataJson.put("bssid",wifi.getBssid());
            dataJson.put("essid",wifi.getEssid());
            dataJson.put("security",wifi.getSecurity());

            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(dataJson.toString());
            wr.flush();

            StringBuilder sb = new StringBuilder();
            int HttpResult = conn.getResponseCode();
            if (HttpResult == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(conn.getInputStream(), "utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                System.out.println("" + sb.toString());
            } else {
                System.out.println(conn.getResponseMessage());
            }

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
        Toast.makeText()
        return result;
    }
    */


}
