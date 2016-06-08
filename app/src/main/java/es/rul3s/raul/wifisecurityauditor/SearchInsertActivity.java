package es.rul3s.raul.wifisecurityauditor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
}
