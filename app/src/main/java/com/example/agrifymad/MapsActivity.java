package com.example.agrifymad;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.agrifymad.databinding.ActivityMapsBinding;

import java.util.ArrayList;
import java.util.Vector;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    MarkerOptions marker;
    LatLng centerLocation;

    Vector<MarkerOptions> markerOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        centerLocation = new LatLng(3.12824,101.65085);

        markerOptions = new Vector<>();

        markerOptions.add(new MarkerOptions().title("Kimo Farm")
                .position(new LatLng(3.12179,101.64015))
                .snippet("Open from 10am - 6pm"));

        markerOptions.add(new MarkerOptions().title("Fati Farm")
                .position(new LatLng(3.11519,101.61050))
                .snippet("Open from 10am - 5pm"));

        markerOptions.add(new MarkerOptions().title("Pelo Farm")
                .position(new LatLng(3.11199,101.62178))
                .snippet("Open from 9am - 6pm"));

        markerOptions.add(new MarkerOptions().title("Kari Farm")
                .position(new LatLng(3.12022,101.62126))
                .snippet("Open from 9am - 5pm"));

        markerOptions.add(new MarkerOptions().title("Ona Farm")
                .position(new LatLng(3.13005,101.63440))
                .snippet("Open from 8am - 5pm"));

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

        for (MarkerOptions mark: markerOptions) {
            mMap.addMarker(mark);
        }

        enableMyLocation();

        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setZoomGesturesEnabled(true);
        googleMap.getUiSettings().setScrollGesturesEnabled(true);
        googleMap.getUiSettings().setRotateGesturesEnabled(false);




        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centerLocation,8));
    }


    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    private void enableMyLocation() {
        // 1. Check if permissions are granted, if so, enable the my location layer
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);

        } else {
            String perms[] = {"android.permission.ACCESS_FINE_LOCATION","android.permission.ACCESS_COARSE_LOCATION"};
            // 2. Otherwise, request location permissions from the user.
            ActivityCompat.requestPermissions(this,perms,200);
        }
    }
}