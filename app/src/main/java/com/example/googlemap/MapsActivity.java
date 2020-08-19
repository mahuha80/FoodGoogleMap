package com.example.googlemap;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private EditText edSearch;
    private Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        edSearch = findViewById(R.id.ed_search);
        btnSearch = findViewById(R.id.btn_Search);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        btnSearch.setOnClickListener(view -> {
            ProgressDialog progress = new ProgressDialog(this);
            progress.setMessage("Searching ...");
            progress.show();
            String name = edSearch.getText().toString();
            API api = RetrofitInstance.getInstance().create(API.class);
            api.getLocation(name).enqueue(new Callback<RootModel>() {
                @Override
                public void onResponse(Call<RootModel> call, Response<RootModel> response) {
                    RootModel rootModel = response.body();
                    List<Result> list = rootModel.getResults();
                    for (int i = 0; i < list.size(); i++) {
                        Geometry geometry = list.get(i).getGeometry();
                        LatLng latLongSearch = new LatLng(geometry.getLocation().getLat(), geometry.getLocation().getLng());
                        mMap.addMarker(new MarkerOptions().position(latLongSearch));
                    }
                    progress.hide();
                }

                @Override
                public void onFailure(Call<RootModel> call, Throwable t) {

                }
            });
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng haNoi = new LatLng(21.027763, 105.834160);
        mMap.addMarker(new MarkerOptions().position(haNoi).title("Ha Noi"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(haNoi));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(haNoi));
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(haNoi)      // Sets the center of the map to location user
                .zoom(17)                   // Sets the zoom
                .bearing(90)                // Sets the orientation of the camera to east
                .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}