package com.example.guessthecelebrity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    TextView textView;

    public void chooseName(View view) {
        DownloadTask task = new DownloadTask();
        String result = null;

        try {
            result = task.execute("https://svenskainfluencers.nu/kandisar/").get();
            //Log.i("Result = " , result);
            //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            textView.setText(result);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        linearLayout.setVisibility(View.VISIBLE);

    }

    public class DownloadTask extends AsyncTask <String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            String Result ="";
            URL url;
            HttpURLConnection urlConnection = null;

            try {

                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while (data != -1){
                    char current = (char) data;
                    Result += current;
                    data = reader.read();
                }
                return Result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        DownloadTask task = new DownloadTask();
        //String result = null;

        try {
            result = task.execute("https://svenskainfluencers.nu/kandisar/").get();
            //Log.i("Result = " , result);
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

         */
    }
}