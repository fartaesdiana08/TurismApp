package com.example.turismapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.turismapp.room.AsyncTaskRunner;
import com.example.turismapp.room.Callback;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TransportActivity extends AppCompatActivity {

    private static String URL_JSON="https://jsonkeeper.com/b/ZAOJ";

    private ListView lvTransporturi;
    private ArrayList<Transport> transporturi=new ArrayList<>();

    private AsyncTaskRunner asyncTaskRunner=new AsyncTaskRunner();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);
        lvTransporturi=findViewById(R.id.listView);

        addTransporturiAdapter();
        getTransporturiJson();
    }

    private void getTransporturiJson(){
        Callable<String> asyncOperation = new ManagerHttp(URL_JSON);
        Callback<String> mainThreadOperation = receiveTransportfromHttp();

        asyncTaskRunner.executeAsync(asyncOperation, mainThreadOperation);
    }

    private Callback<String> receiveTransportfromHttp() {
        return new Callback<String>() {
            @Override
            public void runResultOnUiThread(String result) {
                transporturi.addAll(TransportJsonParser.fromJson(result));
                notifyAdapter();
            }
        };
    }

    private void addTransporturiAdapter(){
        TransportAdapter adapter=new TransportAdapter(getApplicationContext(),R.layout.custom_adapter_transport, transporturi, getLayoutInflater());
        lvTransporturi.setAdapter(adapter);
    }
    private void notifyAdapter(){
        ArrayAdapter adapter=(ArrayAdapter)lvTransporturi.getAdapter();
        adapter.notifyDataSetChanged();
    }
}