package com.example.listycitylab3;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AddCityFragment.AddCityDialogueListener, EditCityFragment.EditCityDialogueListener{

    private ArrayList<City> dataList;
    private ListView cityList;
    private CityArrayAdapter cityAdapter;
    private int clickedIndex = -1;

    public void addCity(City city)
    {
        cityAdapter.add(city);
        cityAdapter.notifyDataSetChanged();
    }

    public void editCity(City city)
    {
        cityAdapter.getItem(clickedIndex).setName(city.getName());
        cityAdapter.getItem(clickedIndex).setProvince(city.getProvince());
        cityAdapter.notifyDataSetChanged();
    }

    public City getCurrCity()
    {
        if(clickedIndex != -1) {
            return cityAdapter.getItem(clickedIndex);
        }
        return new City("ERROR CITY", "BAD PROV");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] cities = {
                "Edmonton", "Vancouver", "Toronto",
        };

        String[] provinces = {
                "AB", "BC", "ON",
        };

        dataList = new ArrayList<>();
        //iterate through our lists
        for(int i = 0; i < cities.length; ++i)
        {
            dataList.add(new City(cities[i], provinces[i]));
        }
        
        cityList = findViewById(R.id.city_list);
        cityAdapter = new CityArrayAdapter(this, dataList);
        cityList.setAdapter(cityAdapter);

        FloatingActionButton fab = findViewById(R.id.button_add_city);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddCityFragment().show(getSupportFragmentManager(), "Add City");
            }
        });

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                clickedIndex = i;
                new EditCityFragment().show(getSupportFragmentManager(), "Edit City");
            }
        });
    }
}