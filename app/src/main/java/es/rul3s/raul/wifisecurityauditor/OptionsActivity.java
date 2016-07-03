package es.rul3s.raul.wifisecurityauditor;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;

public class OptionsActivity extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Log.d("INFO","punto1");
        addPreferencesFromResource(R.xml.preferences);
        //Log.d("INFO","punto2");
    }
}
