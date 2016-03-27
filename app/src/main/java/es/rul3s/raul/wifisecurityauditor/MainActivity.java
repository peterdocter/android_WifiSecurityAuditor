package es.rul3s.raul.wifisecurityauditor;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent changeAct;
        switch (item.getItemId()) {
            case R.id.menu_Settings:
                changeAct = new Intent(this,SettingsActivity.class);
                startActivity(changeAct);
                return true;
            case R.id.menu_About:
                changeAct = new Intent(this,AboutActivity.class);
                startActivity(changeAct);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void btScan(View view){
        Intent changeAct = new Intent(this,ScanActivity.class);
        startActivity(changeAct);
    }


}
