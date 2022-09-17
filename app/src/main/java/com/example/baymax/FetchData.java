package com.example.baymax;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baymax.ui.tools.ToolsFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class FetchData extends AsyncTask<String,String,String> {

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        ToolsFragment.textView1.setText(result);
    }

    @Override
    protected String doInBackground(String... urls) {
        HttpURLConnection httpURLConnection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(urls[0]);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            InputStream stream = httpURLConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            String line = "";
            StringBuffer buffer = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            String FinalJson = buffer.toString();
            JSONObject jsonObject = new JSONObject(FinalJson);
            JSONArray parentArray = jsonObject.getJSONArray("employees");
            JSONObject finalObject = parentArray.getJSONObject(0);
            String Name = finalObject.getString("name");
            String About = finalObject.getString("about");
            String Email = finalObject.getString("email");
            String Request = finalObject.getString("request");
            return Name + "\n " + About + "\n" + Email + "\n\n\n" + Request;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;


    }
}