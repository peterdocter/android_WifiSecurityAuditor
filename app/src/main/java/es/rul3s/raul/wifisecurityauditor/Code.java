package es.rul3s.raul.wifisecurityauditor;

/**
 * Created by Raul on 16/06/2016.
 */
public class Code {
}

/*
import android.os.AsyncTask;
import java.io.IOException;


public class JSONAsyncTask extends AsyncTask<String, String, String>{

    @Override
    protected String doInBackground(String... uri) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response;
        String responseString = null;
        try {
            response = httpclient.execute(new HttpGet(uri[0]));
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                responseString = out.toString();
                out.close();
            } else{
                //Closes the connection.
                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }
        } catch (ClientProtocolException e) {
            //TODO Handle problems..
        } catch (IOException e) {
            //TODO Handle problems..
        }
        return responseString;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        //Do anything with response..
    }
}

public void getJsonVolley(View view){
        EditText etUrl = (EditText)findViewById(R.id.json_etUrl);
        url = etUrl.getText().toString();
        tvResp.setText("Connecting to:\n" +url);
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        tvResp.setText(tvResp.getText().toString() +"\nResponse is: "+ response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvResp.setText(tvResp.getText().toString() +"\nThat didn't work!\n" +error.toString());
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

public void getJsonHttpUrl(View view){
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL("http://www.mysite.se/index.asp?data=99");

            urlConnection = (HttpURLConnection) url
                    .openConnection();

            InputStream in = urlConnection.getInputStream();

            InputStreamReader isw = new InputStreamReader(in);

            int data = isw.read();
            while (data != -1) {
                char current = (char) data;
                data = isw.read();
                System.out.print(current);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }
*/