package com.example.turismapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.turismapp.istoric.IstoricSpinner1;
import com.example.turismapp.istoric.IstoricSpinner2;
import com.example.turismapp.istoric.IstoricSpinner3;

public class IstoricActivity extends AppCompatActivity {

    Spinner istoric;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_istoric);

        istoric=findViewById(R.id.spinnerIstoric);
        ArrayAdapter<CharSequence> adapterCazari=ArrayAdapter.createFromResource(getApplicationContext(), R.array.istoricArray, android.R.layout.simple_list_item_1);
        istoric.setAdapter(adapterCazari);

        istoric.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            ArrayAdapter<CharSequence> adapterSwitch;
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("Alege perioada")){

                }
                else{
                    String item=parent.getItemAtPosition(position).toString();

                    if(parent.getItemAtPosition(position).equals("Primele mențiuni")){
                        Intent intent=new Intent(IstoricActivity.this, IstoricSpinner1.class);
                        startActivity(intent);
                    }
                    if(parent.getItemAtPosition(position).equals("Perioada comunistă")){
                        Intent intent=new Intent(IstoricActivity.this, IstoricSpinner2.class);
                        startActivity(intent);
                    }
                    if(parent.getItemAtPosition(position).equals("Prezent")){
                        Intent intent=new Intent(IstoricActivity.this, IstoricSpinner3.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}