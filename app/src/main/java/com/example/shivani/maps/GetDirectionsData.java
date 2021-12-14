package com.example.harish.maps;

/**
 * Created by Shreyas on 06-01-2018.
 */
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import java.io.IOException;
import java.nio.DoubleBuffer;
import java.util.HashMap;
import java.util.List;

/**
 * @auth Priyanka
 */

public class GetDirectionsData extends AsyncTask<Object,String,String> {

    GoogleMap mMap;
    String url;
    String googleDirectionsData;
    String duration, distance;
    LatLng latLng;
    Context context;
    @Override
    protected String doInBackground(Object... objects) {
        mMap = (GoogleMap)objects[0];
        url = (String)objects[1];
        latLng = (LatLng)objects[2];
        context=(Context)objects[3];



        DownloadUrl downloadUrl = new DownloadUrl();
        try {
            googleDirectionsData = downloadUrl.readUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return googleDirectionsData;
    }

    @Override
    protected void onPostExecute(String s) {
    mMap.clear();
        String[] directionsList;
        DataParser parser = new DataParser();
        directionsList = parser.parseDirections(s);
        displayDirection(directionsList);
        HashMap<String,String> directions=new HashMap<>();
        directions = parser.parseTime(s);
        duration=directions.get("duration");
        distance=directions.get("distance");
        Log.e("dist",distance+" abc");

        MarkerOptions markerOptions=new MarkerOptions();
        markerOptions.title("Distance = "+distance);
        markerOptions.snippet("Duration = "+duration);
        markerOptions.position(latLng);
        mMap.addMarker(markerOptions);
    }

    public void displayDirection(String[] directionsList)
    {
        if(directionsList!=null) {
            int count = directionsList.length;
            for (int i = 0; i < count; i++) {
                PolylineOptions options = new PolylineOptions();
                options.color(Color.parseColor("#2E7EF8"));
                options.width(20);
                options.addAll(PolyUtil.decode(directionsList[i]));

                mMap.addPolyline(options);
            }
        }
        else{
            Toast.makeText(context,"No routes found",Toast.LENGTH_SHORT).show();}
    }






}
