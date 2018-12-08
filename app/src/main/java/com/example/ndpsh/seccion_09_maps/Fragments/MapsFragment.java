package com.example.ndpsh.seccion_09_maps.Fragments;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ndpsh.seccion_09_maps.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MapsFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerDragListener {


    private GoogleMap gMap;
    private MapView mapView;

    private List<Address> addresses;
    private Geocoder geocoder;

    private MarkerOptions marker;


    public MapsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);

        mapView = rootView.findViewById(R.id.gMaps);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;

        LatLng place = new LatLng(35.673473752079516, 139.71038800000008);

        //Otra forma de hacer zoom
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);

        marker = new MarkerOptions();
        marker.position(place);
        marker.title("Mi marcador");
        marker.draggable(true);
        marker.snippet("");
        marker.icon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_menu_mylocation));


        gMap.addMarker(marker);
        gMap.moveCamera(CameraUpdateFactory.newLatLng(place));
        gMap.animateCamera(zoom);

        gMap.setOnMarkerDragListener(this);

        geocoder = new Geocoder(getContext(), Locale.getDefault());
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        marker.hideInfoWindow();

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        double latitude = marker.getPosition().latitude;
        double longitude = marker.getPosition().longitude;

        try {
            addresses =  geocoder.getFromLocation(latitude, longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SetInfoMap();

    }

    public void SetInfoMap() {

        String address = addresses.get(0).getAddressLine(0);
        String city = addresses.get(0).getLocality();
        String state = addresses.get(0).getAdminArea();
        String country = addresses.get(0).getCountryName();
        String postalCode = addresses.get(0).getPostalCode();


        Toast.makeText(getContext(),
                "address: " + address + "\n" +
                        "city: " + city + "\n" +
                        "state: " + state + "\n" +
                        "country: " + country + "\n" +
                        "address: " + postalCode
                , Toast.LENGTH_LONG).show();

    }
}
