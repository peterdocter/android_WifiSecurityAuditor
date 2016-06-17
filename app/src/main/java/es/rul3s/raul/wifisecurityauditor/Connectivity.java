package es.rul3s.raul.wifisecurityauditor;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Raul on 16/06/2016.
 */
public class Connectivity extends AsyncTask<String, Void, String> {
    String urlString;
    public Context context;
    //public View rootView;

    public Connectivity(Context context){
        this.context = context;
        //this.rootView = rootView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d("INFO:","PreExecute");
    }

    @Override
    protected String doInBackground(String... params) {
        Log.d("INFO:","DoInBackGround");
        try{
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            urlConnection.disconnect();
            return in.toString();
        }catch(Exception e){
            return ("\nERROR:"+e.toString());
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }
}
