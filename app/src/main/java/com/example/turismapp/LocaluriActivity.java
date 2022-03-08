package com.example.turismapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class LocaluriActivity extends AppCompatActivity {

    Spinner localuri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localuri);

        localuri=findViewById(R.id.spinnerLocaluri);
        ArrayAdapter<CharSequence> adapterLoc=ArrayAdapter.createFromResource(getApplicationContext(), R.array.localuriArray, android.R.layout.simple_list_item_1);
        localuri.setAdapter(adapterLoc);
    }
}