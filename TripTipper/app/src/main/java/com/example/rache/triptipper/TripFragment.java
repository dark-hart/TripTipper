package com.example.rache.triptipper;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class TripFragment extends Fragment implements LocationListener {
    private static final String TAG = "TripFragment";
    private static final int REQUEST_FINE_LOCATION = 1;
    private List<Item> stations;
    public ListItems destinations;
    private View view;
    private Vibrator v;
    private boolean left = false;
    private Location lastLoc = null;
    private String lastStation = "";

/*
    Boolean oakgrove = getActivity().getIntent().getExtras().getBoolean("oakgrove");
    Boolean maldencenter = getActivity().getIntent().getExtras().getBoolean("maldencenter");
    Boolean wellingtonB = getActivity().getIntent().getExtras().getBoolean("wellington");
    Boolean assemblyB = getActivity().getIntent().getExtras().getBoolean("assembly");
    Boolean sullivansquare = getActivity().getIntent().getExtras().getBoolean("sullivansquare");
    Boolean communitycollege = getActivity().getIntent().getExtras().getBoolean("communitycollege");
    Boolean northstation = getActivity().getIntent().getExtras().getBoolean("northstation");
    Boolean haymarketB = getActivity().getIntent().getExtras().getBoolean("haymarket");
    Boolean stateB = getActivity().getIntent().getExtras().getBoolean("state");
    Boolean downtowncrossing = getActivity().getIntent().getExtras().getBoolean("downtowncrossing");
    Boolean chinatownB = getActivity().getIntent().getExtras().getBoolean("chinatown");
*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LayoutInflater inf = getActivity().getLayoutInflater();
        view = inf.inflate(R.layout.trip_fragment, container, false);


        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        initialiseStations();

        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_FINE_LOCATION);
        }
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.VIBRATE)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.VIBRATE}, REQUEST_FINE_LOCATION);
        }
        LocationManager mLocationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);
        v = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);




    }

    private void initialiseStations (){
        stations = new ArrayList<>();
        Item oakGrove, maldenCenter, wellington, assembly, sullivanSquare,communityCollege,
                northStation, haymarket, state, downtownCrossing, chinatown;



        //Orange Line
        oakGrove = new Item();
        oakGrove.setStation("Oak Grove");
        oakGrove.setLat(42.436994);
        oakGrove.setLng(-71.070842);
        stations.add(oakGrove);


        maldenCenter = new Item();
        maldenCenter.setStation("Malden Center");
        maldenCenter.setLat(42.426824);
        maldenCenter.setLng(-71.074324);
        stations.add(maldenCenter);

        wellington = new Item();
        wellington.setStation("Wellington");
        wellington.setLat(42.401724);
        wellington.setLng(-71.077137);
        stations.add(wellington);

        assembly = new Item();
        assembly.setStation("Assembly");
        assembly.setLat(42.392791);
        assembly.setLng(-71.077261);
        stations.add(assembly);

        sullivanSquare = new Item();
        sullivanSquare.setStation("Sullivan Square");
        sullivanSquare.setLat(42.384020);
        sullivanSquare.setLng(-71.074079);
        stations.add(sullivanSquare);

        communityCollege = new Item();
        communityCollege.setStation("Sullivan Square");
        communityCollege.setLat(42.384020);
        communityCollege.setLng(-71.074079);
        stations.add(communityCollege);

        northStation = new Item();
        northStation.setStation("North Station");
        northStation.setLat(42.366475);
        northStation.setLng(-71.062168);
        stations.add(northStation);

        haymarket = new Item();
        haymarket.setStation("Haymarket");
        haymarket.setLat(42.362995);
        haymarket.setLng(-71.058335);
        stations.add(haymarket);

        state = new Item();
        state.setStation("State");
        state.setLat(42.357753);
        state.setLng(-71.057363);
        stations.add(state);

        downtownCrossing = new Item();
        downtownCrossing.setStation("Downtown Crossing");
        downtownCrossing.setLat(42.355499);
        downtownCrossing.setLng(-71.060472);
        stations.add(downtownCrossing);

        chinatown = new Item();
        chinatown.setStation("Chinatown");
        chinatown.setLat(42.352436);
        chinatown.setLng(-71.062573);
        //chinatown.setChecked(chinatownB);
        stations.add(chinatown);


    }

    @Override
    public void onLocationChanged(Location location) {

        destinations = new ListItems();
        String data = destinations.readData();
        JSONParser decodeData = new JSONParser();
        if(data != null && data.length()>0) {
            try {
                destinations.items = decodeData.getData(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        TextView stationText = view.findViewById(R.id.stationText);
        for (Iterator<Item> i = stations.iterator(); i.hasNext();) {
            Item iItem = i.next();
            Location loc1 = new Location("");
            loc1.setLatitude(iItem.getLat());
            loc1.setLongitude(iItem.getLng());

            //If near station, update name
            double radius = 150.0;
            if (loc1.distanceTo(location) < radius) {
                stationText.setText(iItem.getStation());
                lastLoc = loc1;
                lastStation = iItem.getStation();
                left = false;
            }
            if (lastLoc != null && lastLoc.distanceTo(location) > radius && left == false){
                stationText.setText("Departed from " + lastStation);
                stationText.setBackgroundColor(Color.parseColor("#073452"));
                stationText.setTextColor(Color.parseColor("#F2F2F2"));
                left = true;
            }

        }
        //Setup dummy destination Haymarket
        Location locDummy = new Location("");
        locDummy.setLatitude(42.362995);
        locDummy.setLongitude(-71.058335);

        if (locDummy.distanceTo(location) < 150.0 ) {
            v.vibrate(4000);
            stationText.setBackgroundColor(Color.parseColor("#F7F700"));
            stationText.setTextColor(Color.parseColor("#000000"));
            left = false;
        }
        if(!destinations.items.isEmpty()) {
            for (Iterator<Item> i = destinations.items.iterator(); i.hasNext(); ) {
                Item iItem = i.next();
                Location loc1 = new Location("");
                loc1.setLatitude(iItem.getLat());
                loc1.setLongitude(iItem.getLng());
                Toast.makeText(getActivity().getApplicationContext(),iItem.getStation(),Toast.LENGTH_SHORT).show();

                //If near station, buzz
                double radius = 150.0;


                if (loc1.distanceTo(location) < radius) {
                    v.vibrate(4000);
                    stationText.setBackgroundColor(Color.parseColor("#F7F700"));
                    stationText.setTextColor(Color.parseColor("#000000"));
                    left = false;
                }

            }
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.i(TAG, "Provider " + provider + " has now status: " + status);
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.i(TAG, "Provider " + provider + " is enabled");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.i(TAG, "Provider " + provider + " is disabled");
    }

}

