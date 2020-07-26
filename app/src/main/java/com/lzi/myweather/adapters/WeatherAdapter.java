package com.lzi.myweather.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.lzi.myweather.MainActivity;
import com.lzi.myweather.R;
import com.lzi.myweather.entities.Weather;

import java.util.Date;
import java.util.List;

public class WeatherAdapter extends BaseAdapter {
    private  Context context;
    private List<Weather> ListWeather;
    private LayoutInflater inflater;

    public WeatherAdapter(Context context, List<Weather> list_w) {
        this.context = context;
        this.ListWeather = list_w;
    }

    @Override
    public int getCount() {
        return ListWeather.size();
    }

    @Override
    public Weather getItem(int position) {
        return ListWeather.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_weather_adapter,null);

        Weather weather = ListWeather.get(position);

        String date = weather.getDate();
        double min_temp = weather.getMin_temperature();
        double max_temp = weather.getMax_temperature();
        double pressure = weather.getPressure();
        int humidity = weather.getHumidity();

        TextView tv_date = convertView.findViewById(R.id.tv_datetime);
        tv_date.setText(date+"");

        TextView tv_min = convertView.findViewById(R.id.tv_min);
        tv_min.setText(tv_min+"");

        TextView tv_max = convertView.findViewById(R.id.tv_max);
        tv_min.setText(tv_max+"");

        TextView tv_pressure = convertView.findViewById(R.id.tv_pressure);
        tv_min.setText(tv_pressure+"");

        TextView tv_humidity = convertView.findViewById(R.id.tv_humidity);
        tv_min.setText(tv_humidity+"");

        return convertView;
    }
}
