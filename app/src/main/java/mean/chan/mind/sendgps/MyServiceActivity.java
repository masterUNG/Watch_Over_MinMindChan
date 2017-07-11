package mean.chan.mind.sendgps;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MyServiceActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String[] loginStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_layout);

        //Get Value From Intent
        getValueFromIntent();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        addMapFragment();

        //Back Controller
        backController();

        //Save Controller
        saveController();


    }   // Main Method

    private void saveController() {
        ImageView imageView = (ImageView) findViewById(R.id.imvSave);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyServiceActivity.this, AddChildActivity.class);
                intent.putExtra("Login", loginStrings);
                startActivity(intent);
            }
        });
    }

    private void backController() {
        ImageView imageView = (ImageView) findViewById(R.id.imvBack);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getValueFromIntent() {
        loginStrings = getIntent().getStringArrayExtra("Login");
    }

    private void addMapFragment() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }   // onMapReady

}   // Main Class
