package es.rul3s.raul.wifisecurityauditor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class VulnerabilitiesActivity extends Activity {
    String security;
    TextView tvResult, tvCipherResult, tvWpsResult;
    String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vulnerabilities);

        Intent intent = getIntent();
        security = intent.getStringExtra("security");

        tvCipherResult = (TextView) findViewById(R.id.vuln_tvCipher);
        tvWpsResult = (TextView) findViewById(R.id.vuln_tvWps);
        tvResult = (TextView) findViewById(R.id.vuln_tvResult);

        //Toast.makeText(this,security,Toast.LENGTH_SHORT).show();
        checkSecurity();
    }

    public void checkSecurity(){
        boolean vulnerable = false;
        ImageView img= (ImageView) findViewById(R.id.vuln_ivResult);

        //result += getString(R.string.vuln_checkCipher) +"\n";

        if(security.contains("WEP")){
            tvCipherResult.setText(getString(R.string.vuln_advWep));
            vulnerable = true;
        }else if(security.contains("WPA") && !security.contains("WPA2")){
            tvCipherResult.setText(getString(R.string.vuln_advWpa));
            vulnerable = true;
        }else{
            tvCipherResult.setText(getString(R.string.vuln_cipherOk));
        }

        if(security.contains("WPS")){
            tvWpsResult.setText(getString(R.string.vuln_advWPS));
            vulnerable = true;
        }else{
            tvWpsResult.setText(getString(R.string.vuln_wpsOk));
        }

        if(!vulnerable){
            tvResult.setText(getString(R.string.vuln_ok));
            img.setImageResource(R.drawable.ok);

        }else{
            img.setImageResource(R.drawable.warning);
        }
    }
}
