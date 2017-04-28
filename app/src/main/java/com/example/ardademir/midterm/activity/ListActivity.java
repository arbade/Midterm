package com.example.ardademir.midterm.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ardademir.midterm.R;
import com.example.ardademir.midterm.adapter.VenueAdapter;
import com.example.ardademir.midterm.model.FoursquareResponse;
import com.example.ardademir.midterm.model.Venue;
import com.example.ardademir.midterm.service.RetroClient;
import com.example.ardademir.midterm.service.RetroInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by ardademir on 27.04.2017.
 */

public class ListActivity extends AppCompatActivity {

    ProgressDialog pd;
    ListView lvPlaces;
    VenueAdapter venueAdapter;
    String client_id = "GX4EFXOJGY4YHXFK3P1T2YTFRURSPL3QTXF0BQTKFF3WMVBF";
    String client_secret = "LI2INEYUEMY5BYJ13DBAR25032YGD502UUYZN5QXGEEL2PG5";
    String api_version ="20161016";
    String ll;
    FoursquareResponse foursquareResponse;
    List<Venue> venue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        lvPlaces = (ListView) findViewById(R.id.lvPlaces);
        double lat = getIntent().getExtras().getDouble("lat");
        double lng = getIntent().getExtras().getDouble("lng");
        ll = String.valueOf(lat)+","+lng;

        Log.i("INFO", "HAZIR = "+ll);

        pd = new ProgressDialog(ListActivity.this);
        pd.setMessage("Lütfen bekleyin.");
        pd.show();

        RetroInterface retroInterface = RetroClient.getClient().create(RetroInterface.class);
        Call<FoursquareResponse> call = retroInterface.getVenueJson(client_id,client_secret,api_version,ll);
        call.enqueue(new Callback<FoursquareResponse>() {
            @Override
            public void onResponse(Call<FoursquareResponse> call, Response<FoursquareResponse> response) {
                foursquareResponse = response.body();
                int code = foursquareResponse.getMeta().getCode();

                    venue = foursquareResponse.getResponse().getVenues();
                    venueAdapter = new VenueAdapter(getApplicationContext(),venue);
                    lvPlaces.setAdapter(venueAdapter);



                pd.dismiss();
            }

            @Override
            public void onFailure(Call<FoursquareResponse> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(getApplicationContext(), "Bağlantı yok!", Toast.LENGTH_SHORT).show();
            }
        });

        lvPlaces.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = venue.get(position).getName();
                double lat = venue.get(position).getLocation().getLat();
                double lng = venue.get(position).getLocation().getLng();

                Intent maps = new Intent(ListActivity.this, MapsActivity.class);
                maps.putExtra("lat",lat);
                maps.putExtra("lng", lng);
                maps.putExtra("name", name);
                startActivity(maps);
            }
        });



    }
}
