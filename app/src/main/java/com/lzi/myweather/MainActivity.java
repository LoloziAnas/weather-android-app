package com.lzi.myweather;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

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
    private Button search_btn;

    private List<Weather> list_weather = new ArrayList<>();;
    private WeatherAdapter weatherAdapter;

    private RequestQueue queue;
    private StringRequest stringRequest;

    private static final String URL = "https://samples.openweathermap.org/data/2.5/forecast?q=";
    private static final String KEY = "&appid=0c8e90125f58726c059d6503a7270257";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtSearch = findViewById(R.id.edt_search);
        listWeathers = findViewById(R.id.list_weathers);
        search_btn = findViewById(R.id.btn_search);

        weatherAdapter = new WeatherAdapter(this,list_weather);

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });
        listWeathers.setAdapter(weatherAdapter);
    }

    private void jsonToList(JSONObject jsonObject){

    }
    private String dateToString(Date date){
        final String format = "dd-MM-yyyy'T'HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
    private void search() {
        //Clean the list of weather
        list_weather.clear();
        weatherAdapter.notifyDataSetChanged();


        String city = edtSearch.getText().toString();
        String f_url = URL+city+KEY;

        Log.i("URL",f_url);
        //RequestQueue initialized

        queue = Volley.newRequestQueue(this);

        //String Request initialized
        stringRequest = new StringRequest(Request.Method.GET, f_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("RESPONSE",response);
                        try {
                            //display the response on screen as a Toast
                            Toast.makeText(getApplicationContext(),"Response :" + response.toString(), Toast.LENGTH_LONG).show();

                            //Create a JSONObject to store the response from the API
                            JSONObject jsonObject = new JSONObject(response);

                            Weather weather = new Weather();

                            //Create a JSONArray object to put the information of "list"
                            JSONArray jsonArray = jsonObject.getJSONArray("list");

                            //Parsing JSON
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

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        // notifying list adapter about data changes so that it renders the list view with updated data
                        weatherAdapter.notifyDataSetChanged();
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
