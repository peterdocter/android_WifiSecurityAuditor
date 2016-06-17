package es.rul3s.raul.wifisecurityauditor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class VulnerabilitiesActivity extends Activity {
    String security;
    TextView tvResult;
    String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vulnerabilities);

        Intent intent = getIntent();
        security = intent.getStringExtra("security");

        tvResult = (TextView) findViewById(R.id.vuln_tvResult);

        //Toast.makeText(this,security,Toast.LENGTH_SHORT).show();
        checkSecurity();
    }

    public void checkSecurity(){
        boolean vulnerable = false;
        ImageView img= (ImageView) findViewById(R.id.vuln_ivResult);

        result += getString(R.string.vuln_checkCipher) +"\n";

        if(security.contains("WEP")){
            result += getString(R.string.vuln_advWep);
            vulnerable = true;
        }

        if(security.contains("WPA") && !security.contains("WPA2")){
            result += getString(R.string.vuln_advWpa);
            vulnerable = true;
        }

        result += getString(R.string.vuln_checkWps) +"\n";

        if(security.contains("WPS")){
            result += getString(R.string.vuln_advWPS);
            vulnerable = true;
        }



        if(!vulnerable){
            result += R.string.vuln_ok;
            img.setImageResource(R.drawable.ok);
        }else{
            img.setImageResource(R.drawable.warning);
        }

        tvResult.setText(result);
    }
}
