package com.example.turismapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CazariActivity extends AppCompatActivity {

    Spinner sortare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cazari);

        sortare = findViewById(R.id.spinnerCazari);
        ArrayAdapter<CharSequence> adapterCazari=ArrayAdapter.createFromResource(getApplicationContext(), R.array.cazariArray, android.R.layout.simple_list_item_1);
        sortare.setAdapter(adapterCazari);
    }
}