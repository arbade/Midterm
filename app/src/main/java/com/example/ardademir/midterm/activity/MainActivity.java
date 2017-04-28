package com.example.ardademir.midterm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ardademir.midterm.R;
import com.example.ardademir.midterm.service.GPSTracker;


/**
 * Created by ardademir on 27.04.2017.
 */

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    GPSTracker gps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gps = new GPSTracker(MainActivity.this);
                if(gps.canGetLocation()) {
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    if(longitude == 0 && latitude == 0) {
                        Toast.makeText(getApplicationContext(), "Lütfen Bekleyin...", Toast.LENGTH_SHORT).show();
                    } else {
                        gps.stopUsingGPS();
                        Intent list = new Intent(MainActivity.this, ListActivity.class);
                        list.putExtra("lat", latitude);
                        list.putExtra("lng", longitude);
                        startActivity(list);
                    }

                } else {
                    gps.showSettingsAlert();
                }
            }
        });

    }

}
