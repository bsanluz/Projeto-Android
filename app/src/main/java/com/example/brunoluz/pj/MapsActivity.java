package com.example.brunoluz.pj;

import android.content.Intent;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    double lat;
    double lon;
    String address;
    String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent i = getIntent();
        address =i.getStringExtra("endereco")+",Brasil";
        nome = i.getStringExtra("nome");
    }


 
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Geocoder geocoder = new Geocoder(this);
        try {
            List<android.location.Address> list = geocoder.getFromLocationName(address,1);
            android.location.Address add= list.get(0);
            lat = add.getLatitude();
            lon = add.getLongitude();

        } catch (IOException e) {
            e.printStackTrace();
        }
       LatLng ende = new LatLng(lat, lon);
        Marker ender = mMap.addMarker(new MarkerOptions().position(ende).title(nome));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ende));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
    }
}
