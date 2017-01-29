package com.example.shaloin.fourteenthassignmenta;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.util.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String url="http://api.openweathermap.org/data/2.5/weather?q=London,uk&appid=9e7a1595f2c760906ef2363b6d265308";

    private Button load;
    private TextView t_temp,t_pressure,t_huidity;
    private ProgressBar progressBar;
    private LinearLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        load=(Button)findViewById(R.id.getWeatherASyncButtonID);
        t_temp=(TextView)findViewById(R.id.tempTextID);
        t_pressure=(TextView)findViewById(R.id.pressureTextID);
        t_huidity=(TextView)findViewById(R.id.humidityTextID);
        progressBar=(ProgressBar)findViewById(R.id.progressBarID);
        layout=(LinearLayout)findViewById(R.id.linearID);

        load.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        getWeatherAsync();

    }

    private void getWeatherAsync(){
        layout.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);

        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {



                try{
                    String responseString=response.body().string();

                    JSONObject jsonObject=new JSONObject(responseString);
                    Gson gson=new Gson();
                    final Model model=gson.fromJson(jsonObject.toString(),Model.class);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                                updateUI(model);
                        }
                    });

                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        });

    }

    public void updateUI(Model model){

        progressBar.setVisibility(View.GONE);
        if (model!=null){
            layout.setVisibility(View.VISIBLE);
            t_temp.setText("Temperature : "+model.getMain().getTemp());
            t_pressure.setText("Pressure : "+model.getMain().getPressure());
            t_huidity.setText("Humidity : "+model.getMain().getHumidity());

        }

    }
}
