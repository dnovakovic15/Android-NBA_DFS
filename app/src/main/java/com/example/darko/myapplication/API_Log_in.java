package com.example.darko.myapplication;

/**
 * Created by Darko on 11/6/2016.
 */

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


class API_Log_in extends AsyncTask<String, Object, String> {

    private String result;


    @Override
    protected void onPreExecute() {
    }

    protected String doInBackground(String... params) {
        URL url;
        HttpURLConnection conn = null;
        String line;

        //Connects to server to pull up the requested Players's stats.
        try {
            url = new URL("http://52.15.238.167/project/signin.php");
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            //conn.setRequestMethod("POST");
            OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
            StringBuilder sBuilder = new StringBuilder();
            //Pass the position and team abbreviation.
            writer.write("&email=" + params[0]);
            writer.write("&passcode=" + params[1]);
            writer.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            while ((line = reader.readLine()) != null) {
                sBuilder.append(line + "\n");
            }
            result = sBuilder.toString();
            writer.close();
            reader.close();
        }

        catch (MalformedURLException e) {
            System.out.print(e);
        }
        catch (IOException e) {
            System.out.print(e);
        } finally  {
            conn.disconnect();
        }
        System.out.println("Result: " + result);
        return result;
    }

    public void onPostExecute(String result) {
    }

}

