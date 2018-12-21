package com.example.rache.triptipper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class MenuFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {
    private View view;
    ListItems destinations = new ListItems();

    Switch oakGrove;
    Switch maldenCenter;
    Switch wellington;
    Switch assembly;
    Switch sullivanSquare;
    Switch communityCollege;
    Switch northStation;
    Switch haymarket;
    Switch state;
    Switch downtownCrossing;
    Switch chinaTown;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LayoutInflater inf = getActivity().getLayoutInflater();
        view = inf.inflate(R.layout.menu_fragment, container, false);

        oakGrove = view.findViewById(R.id.oakGrove);
        maldenCenter = view.findViewById(R.id.maldenCenter);
        wellington = view.findViewById(R.id.wellington);
        assembly = view.findViewById(R.id.assembly);
        sullivanSquare = view.findViewById(R.id.sullivanSquare);
        communityCollege = view.findViewById(R.id.communityCollege);
        northStation = view.findViewById(R.id.northStation);
        haymarket = view.findViewById(R.id.haymarket);
        state = view.findViewById(R.id.state);
        downtownCrossing = view.findViewById(R.id.downtownCrossing);
        chinaTown = view.findViewById(R.id.chinaTown);



        oakGrove.setOnCheckedChangeListener(this);
        maldenCenter.setOnCheckedChangeListener(this);
        assembly.setOnCheckedChangeListener(this);
        sullivanSquare.setOnCheckedChangeListener(this);
        communityCollege.setOnCheckedChangeListener(this);
        northStation.setOnCheckedChangeListener(this);
        haymarket.setOnCheckedChangeListener(this);
        state.setOnCheckedChangeListener(this);
        downtownCrossing.setOnCheckedChangeListener(this);
        chinaTown.setOnCheckedChangeListener(this);

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        switch (buttonView.getId()) {
            case R.id.oakGrove:
                if (oakGrove.isChecked()) {
                    JSONArray jsonArray = new JSONArray();
                    JSONObject obj = new JSONObject();
                    try {
                        obj.put("station", "OakGrove");
                        obj.put("lat", 42.436994);
                        obj.put("lng", -71.070842);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    jsonArray.put(obj);
                    String tmp = jsonArray.toString();
                    destinations.writeData(tmp);

                }
                break;
            case R.id.maldenCenter:
                if (isChecked) {
                    JSONArray jsonArray = new JSONArray();
                    JSONObject obj = new JSONObject();
                    try {
                        obj.put("station", "Malden Center");
                        obj.put("lat", 42.426824);
                        obj.put("lng", -71.074324);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    jsonArray.put(obj);
                    String tmp = jsonArray.toString();
                    destinations.writeData(tmp);
                }
                break;
            case R.id.wellington:
                if (isChecked) {
                    if (isChecked) {
                        JSONArray jsonArray = new JSONArray();
                        JSONObject obj = new JSONObject();
                        try {
                            obj.put("station", "Wellington");
                            obj.put("lat", 42.401724);
                            obj.put("lng", -71.077137);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        jsonArray.put(obj);
                        String tmp = jsonArray.toString();
                        destinations.writeData(tmp);
                    }
                }
                break;
            case R.id.assembly:
                if (isChecked) {
                    JSONArray jsonArray = new JSONArray();
                    JSONObject obj = new JSONObject();
                    try {
                        obj.put("station", "Assembly");
                        obj.put("lat", 42.392791);
                        obj.put("lng", -71.077261);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    jsonArray.put(obj);
                    String tmp = jsonArray.toString();
                    destinations.writeData(tmp);
                }
                break;
            case R.id.sullivanSquare:
                if (isChecked) {
                    JSONArray jsonArray = new JSONArray();
                    JSONObject obj = new JSONObject();
                    try {
                        obj.put("station", "Sullivan Square");
                        obj.put("lat", 42.373847);
                        obj.put("lng", -71.069674);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    jsonArray.put(obj);
                    String tmp = jsonArray.toString();
                    destinations.writeData(tmp);
                }
                break;
            case R.id.communityCollege:
                if (isChecked) {
                    JSONArray jsonArray = new JSONArray();
                    JSONObject obj = new JSONObject();
                    try {
                        obj.put("station", "Community College");
                        obj.put("lat", 42.384020);
                        obj.put("lng", -71.074079);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    jsonArray.put(obj);
                    String tmp = jsonArray.toString();
                    destinations.writeData(tmp);
                }
                break;
            case R.id.northStation:
                if (isChecked) {
                    JSONArray jsonArray = new JSONArray();
                    JSONObject obj = new JSONObject();
                    try {
                        obj.put("station", "North Station");
                        obj.put("lat", 42.366475);
                        obj.put("lng", -71.062168);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    jsonArray.put(obj);
                    String tmp = jsonArray.toString();
                    destinations.writeData(tmp);
                }
                break;
            case R.id.haymarket:
                if (isChecked) {
                    JSONArray jsonArray = new JSONArray();
                    JSONObject obj = new JSONObject();
                    try {
                        obj.put("station", "Haymarket");
                        obj.put("lat", 42.362995);
                        obj.put("lng", -71.058335);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    jsonArray.put(obj);
                    String tmp = jsonArray.toString();
                    destinations.writeData(tmp);
                }
                break;
            case R.id.state:
                if (isChecked) {
                    Toast.makeText(getActivity().getApplicationContext(),"State added to destinations",
                            Toast.LENGTH_SHORT).show();
                    JSONObject obj = new JSONObject();
                    JSONArray jsonArray= new JSONArray();
                    String tmp;
                    try {
                        obj.put("station", "State");
                        obj.put("lat", 42.357753);
                        obj.put("lng", -71.057363);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    jsonArray.put(obj);
                    tmp = jsonArray.toString();
                    destinations.writeData(tmp);
                }
                break;
            case R.id.downtownCrossing:
                if (isChecked) {
                    JSONArray jsonArray = new JSONArray();
                    JSONObject obj = new JSONObject();
                    try {
                        obj.put("station", "Downtown Crossing");
                        obj.put("lat", 42.355499);
                        obj.put("lng", -71.060472);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    jsonArray.put(obj);
                    String tmp = jsonArray.toString();
                    destinations.writeData(tmp);
                }
                break;
            case R.id.chinaTown:
                if (isChecked) {
                    JSONArray jsonArray = new JSONArray();
                    JSONObject obj = new JSONObject();
                    try {
                        obj.put("station", "Chinatown");
                        obj.put("lat", 42.352436);
                        obj.put("lng", -71.062573);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    jsonArray.put(obj);
                    String tmp = jsonArray.toString();
                    destinations.writeData(tmp);
                }
                break;

            default:
                break;

        }


    }
}
