package com.example.turismapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.turismapp.room.RecenziiActivity;
import com.example.turismapp.room.VizualizareRecenzii;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void deschidereIstoric(View view) {
        Intent it=new Intent(getApplicationContext(), IstoricActivity.class);
        startActivity(it);
    }

    public void deschidereAtractii(View view) {
        Intent it=new Intent(getApplicationContext(), AtractiiActivity.class);
        startActivity(it);
    } 

    public void deschidereRecenzii(View view) {
        Intent it=new Intent(getApplicationContext(), VizualizareRecenzii.class);
        startActivity(it);
    }

    public void deschidereTraseu(View view) {
        Intent it=new Intent(getApplicationContext(), TraseuGoogleActivity.class);
        startActivity(it);
    }

    public void deschidereLocaluri(View view) {
        Intent it=new Intent(getApplicationContext(), LocaluriActivity.class);
        startActivity(it);
    }

    public void deschidereCazari(View view) {
        Intent it=new Intent(getApplicationContext(), CazariActivity.class);
        startActivity(it);
    }

    public void deschidereTransport(View view) {
        Intent it=new Intent(getApplicationContext(), TransportActivity.class);
        startActivity(it);
    }
}