package com.reds.turk_system;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Ronny on 11/4/2017.
 */

public class RegistrationCheck extends AsyncTask {
    String data;
    Context cox;

    public RegistrationCheck(Context cox){
    this.cox= cox;

    }
    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
    @Override
    protected Object doInBackground(Object[] objects) {
        try{
            String userne = (String)objects[0];
            String pass = (String)objects[1];
            String link = "http://192.168.0.2:8080/registercheck.php?username="+userne;
            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            InputStream stream = conn.getInputStream();
            data =  convertStreamToString(stream);
            data=data.toString().replace("\n","");
            if(data.compareTo("false")==0){
                return data;
            }else{
                link = "http://192.168.0.2:8080/register.php?username="+userne+"&password="+pass;
                url = new URL(link);
                conn = (HttpURLConnection)url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                stream.close();
                stream = conn.getInputStream();
                data =  convertStreamToString(stream);
                data=data.toString().replace("\n","");
                return data;
            }

        }catch (Exception e){
            Log.d("tag","Exception: " + e.getMessage());
            return "unsuccesful";

        }

    }

    @Override
    protected void onPostExecute(Object o) {
        if(o.toString().compareTo("true")==0){
            Toast.makeText(cox,"Registration complete Sign in",Toast.LENGTH_LONG).show();
            cox.startActivity(new Intent(cox,LoginActivity.class));
        }else{
            Toast.makeText(cox,"User all ready exits",Toast.LENGTH_LONG).show();
        }
    }
}
