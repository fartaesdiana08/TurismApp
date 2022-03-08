package com.example.turismapp;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class TraseuGoogleActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traseu_google);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        //aici se desenează elemente pe hartă
        mMap = googleMap;

        // adaugare punct parc
        LatLng parc = new LatLng(46.933839, 26.923999);
        mMap.addMarker(new MarkerOptions().position(parc).title("Parcul Municipal"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(parc, 13));

        // adaugare punct Hotel Roman
        LatLng hotel = new LatLng(46.920508, 26.928931);
        mMap.addMarker(new MarkerOptions().position(hotel).title("Hotel Roman"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hotel, 13));

        // adaugare punct Arhiepiscopie
        LatLng arhiepiscopie = new LatLng(46.917587, 26.932722);
        mMap.addMarker(new MarkerOptions().position(arhiepiscopie).title("Arhiepiscopia Romanului și Bacăului"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hotel, 13));

        // adaugare punct mall
        LatLng mall = new LatLng(46.937447, 26.922282);
        mMap.addMarker(new MarkerOptions().position(mall).title("Roman Value Center"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mall, 13));

        // adaugare punct strand
        LatLng strand = new LatLng(46.915351, 26.933694);
        mMap.addMarker(new MarkerOptions().position(strand).title("Complexul Sportiv și de Agrement Moldova"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(strand, 13));

        // adaugare punct muzeu
        LatLng muzeu = new LatLng(46.926092, 26.931418);
        mMap.addMarker(new MarkerOptions().position(muzeu).title("Muzeul de Artă "));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(muzeu, 13));

        mMap.addPolyline(new PolylineOptions().add(mall).add(parc).add(muzeu).add(hotel).add(arhiepiscopie).add(strand));
    }
}