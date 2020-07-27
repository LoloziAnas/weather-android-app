package com.lzi.myweather;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lzi.myweather.adapters.WeatherAdapter;
import com.lzi.myweather.entities.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.android.volley.Request.Method.GET;

public class MainActivity extends AppCompatActivity {

    private EditText edtSearch;
    private ListView listWeathers;
    private List<Weather> list_weather;
    private WeatherAdapter weatherAdapter;
    private static final String URL = "https://samples.openweathermap.org/data/2.5/forecast?q=";
    private static final String KEY = "&appid=0c8e90125f58726c059d6503a7270257";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtSearch = findViewById(R.id.edt_search);
        listWeathers = findViewById(R.id.list_weathers);
        list_weather = new ArrayList<>();
        weatherAdapter = new WeatherAdapter(this,list_weather);


        listWeathers.setAdapter(weatherAdapter);
    }

    private void showAdapter(List<Weather> list_w){
    }

    private void jsonToList(JSONObject jsonObject){

    }
    private String dateToString(Date date){
        final String format = "dd-MM-yyyy'T'HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
    public void search(View view) {
        list_weather.clear();
        weatherAdapter.notifyDataSetChanged();

        String city = edtSearch.getText().toString();
        String f_url = URL+city+KEY;

        Log.i("URL",f_url);
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, f_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("RESPONSE",response);
                        try {
                            //Create a JSONObject to store the response from the API
                            JSONObject jsonObject = new JSONObject(response);
                            Weather weather = new Weather();
                            //Create a JSONArray object to put the information of "list"
                            JSONArray jsonArray = jsonObject.getJSONArray("list");
                            for (int i =0; i<jsonArray.length(); i++){

                                JSONObject jsonObjectOfList = jsonArray.getJSONObject(i);

                                Date d = new Date(jsonObjectOfList.getLong("dt")*1000);
                                String dateString = dateToString(d);

                                JSONObject jsonObjectOfMain = jsonObjectOfList.getJSONObject("main");
                                double temp_min = jsonObjectOfMain.getDouble("temp_min");
                                double temp_max = jsonObjectOfMain.getDouble("temp_max");
                                double pressure = jsonObjectOfMain.getDouble("pressure");
                                int humidity = jsonObjectOfMain.getInt("humidity");

                                weather.setDate(dateString);
                                weather.setMin_temperature(temp_min);
                                weather.setMax_temperature(temp_max);
                                weather.setPressure(pressure);
                                weather.setHumidity(humidity);
                                list_weather.add(weather);
                            }

                            weatherAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("ERROR","Problem of connection");
            }
        });
        queue.add(stringRequest);

    }
}
