package com.example.demoproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BusStopSearchActivity extends AppCompatActivity {

    String urladdress = "http://ppui0209.dothome.co.kr/stationinfo.php";
    String[] staName;
    String[] staId;
    ListView listView;
    BufferedInputStream is;
    String line=null;
    String result=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_stop_search);

        listView=(ListView) findViewById(R.id.list_View);



        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        collectData();
        CustomListView customListView= new CustomListView(this, staName,staId);
        listView.setAdapter(customListView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(BusStopSearchActivity.this, HomeActivity.class);
                startActivity(in);
            }
        });



    }

    private void collectData()
    {
        try{

            URL url = new URL(urladdress);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            is=new BufferedInputStream(con.getInputStream());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine())!= null){
                sb.append(line+"\n");
            }
            is.close();
            result=sb.toString();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        try{
            JSONArray ja = new JSONArray(result);
            JSONObject jo = null;
            staName= new String[ja.length()];
            staId= new String[ja.length()];


            for(int i=0; i<=ja.length(); i++){
                jo=ja.getJSONObject(i);
                staName[i]=jo.getString("name");
                staId[i]=jo.getString("email");


            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }


    }

}