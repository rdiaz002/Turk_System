package com.reds.turk_system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import java.net.URL;


import java.net.HttpURLConnection;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;


import org.w3c.dom.Text;

/**
 * Created by Ronny on 11/3/2017.
 */

public class SignInCheck extends AsyncTask{
    private EditText username;
    private EditText password;
    private Context context;
    private ProgressBar bar;
    private String data;
    protected SignInCheck (Context cox, EditText user, EditText pass, ProgressBar bar){
        this.context = cox;
        this.username = user;
        this.password = pass;
        this.bar = bar;
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
            String passw = (String)objects[1];
            String link = "http://10.0.2.2:8080/login.php?username="+userne+"&password="+passw;
            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            InputStream stream = conn.getInputStream();
            data =  convertStreamToString(stream);
            return data;

        }catch (Exception e){
            Log.d("tag","Exception: " + e.getMessage());
            return "unsuccesful";

        }

    }

    @Override
    protected void onPostExecute(Object o) {
    // change layout and call an activity if successful.
        String temp[]=o.toString().split("\n");
        ArrayList<String> data = new ArrayList<>();
        Collections.addAll(data, temp);

        if(data.get(0).compareTo("true")==0){
            username.setText("success");
            bar.setVisibility(View.GONE);
            Intent jump = new Intent(context,mainpage.class);
            jump.putStringArrayListExtra("data",data);
            context.startActivity(jump);


        }else{
                bar.setVisibility(View.GONE);
                username.setText("no");
            }

    }
}
