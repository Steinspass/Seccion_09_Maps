package com.example.ndpsh.seccion_09_maps.Activities;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ndpsh.seccion_09_maps.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMinZoomPreference(10);
        mMap.setMaxZoomPreference(18);

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        LatLng tokyo = new LatLng(35.673473752079516, 139.71038800000008);
        mMap.addMarker(new MarkerOptions().position(tokyo).title("Marker in Tokyo").draggable(true));
        CameraPosition camera = new CameraPosition.Builder()
                .target(tokyo)
                .zoom(10)       // limit = 21
                .bearing(90)    //  not limit, 1 rotation max. = 360
                .tilt(75)       // limit = 0-90
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camera));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(tokyo));

       mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
           @Override
           public void onMapClick(LatLng latLng) {
               Toast.makeText(MapsActivity.this, "Click on: \n" +
                       "Lat: "+latLng.latitude +"\n" +
                       "Lon " +latLng.longitude,Toast.LENGTH_SHORT).show();

           }
       });

       mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
           @Override
           public void onMapLongClick(LatLng latLng) {
               Toast.makeText(MapsActivity.this, "Long Click on: \n" +
                       "Lat: "+latLng.latitude +"\n" +
                       "Lon " +latLng.longitude,Toast.LENGTH_LONG).show();

           }
       });

       mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
           @Override
           public void onMarkerDragStart(Marker marker) {

           }

           @Override
           public void onMarkerDrag(Marker marker) {

           }

           @Override
           public void onMarkerDragEnd(Marker marker) {
               Toast.makeText(MapsActivity.this, "Marker dragged to: \n" +
                       "Lat: "+marker.getPosition().latitude +"\n" +
                       "Lon " +marker.getPosition().longitude,Toast.LENGTH_SHORT).show();

           }
       });


    }
}
