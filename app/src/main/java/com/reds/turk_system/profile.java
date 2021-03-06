package com.reds.turk_system;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class profile extends Fragment {
    TextView name, bio, rating, interest;
    ListView proj_list;
    String data = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        name = (TextView) root.findViewById(R.id.Name);
        bio = (TextView) root.findViewById(R.id.Bio_Desc);
        rating = (TextView) root.findViewById(R.id.Rating);
        interest = (TextView) root.findViewById(R.id.interests);
        proj_list = (ListView)root.findViewById(R.id.proj_list);

        name.setText(getActivity().getIntent().getStringArrayListExtra("data").get(2));

        if (data == null) {
            //gatherInformation();
        } else {
            //postData();
        }

        return root;
    }

    public void gatherInformation() {
        @SuppressLint("StaticFieldLeak") AsyncTask info = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    String userne = (String) objects[0];
                    String link = "http://192.168.0.2:8080/registercheck.php?username=" + userne;
                    URL url = new URL(link);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000 /* milliseconds */);
                    conn.setConnectTimeout(15000 /* milliseconds */);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    InputStream stream = conn.getInputStream();
                    data = convertStreamToString(stream);
                    data = data.replace("\n", "");
                } catch (Exception e) {
                    Log.d("tag", "Exception: " + e.getMessage());
                    data = null;
                    return "unsuccesful";

                }
                return data;
            }

            @Override
            protected void onPostExecute(Object o) {
                // take data and split it and place text in proper views!
            }
        }.execute();


    }
    public void postData(){
        // input data methods.

        //generate project list
        generateProects();
    }
    public void generateProects(){
        @SuppressLint("StaticFieldLeak") AsyncTask info = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    String userne = (String) objects[0];
                    String link = "http://192.168.0.2:8080/registercheck.php?username=" + userne;
                    URL url = new URL(link);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000 /* milliseconds */);
                    conn.setConnectTimeout(15000 /* milliseconds */);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    InputStream stream = conn.getInputStream();
                    data = convertStreamToString(stream);
                    data = data.replace("\n", "");
                } catch (Exception e) {
                    Log.d("tag", "Exception: " + e.getMessage());
                    data = null;
                    return "unsuccesful";

                }
                return data;
            }

            @Override
            protected void onPostExecute(Object o) {
                // take data and split it and place text in proper views!
            }
        }.execute();



    }
    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
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


}
