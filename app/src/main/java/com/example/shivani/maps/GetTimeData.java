package com.example.harish.maps;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by Shreyas on 07-01-2018.
 */

public class GetTimeData extends AsyncTask<Object,String,String> {
    GoogleMap mMap;
    String url;
    String directionsData;
    String duration, distance;
    LatLng latLng;

    @Override
    protected String doInBackground(Object... objects) {
        mMap=(GoogleMap)objects[0];
        url=(String) objects[1];
        latLng = (LatLng)objects[2];
        DownloadUrl downloadUrl=new DownloadUrl();
        try
        {
            directionsData=downloadUrl.readUrl(url);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    return directionsData;}

    @Override
    protected void onPostExecute(String s) {
        HashMap<String,String>directionsList=null;

        DataParser parser = new DataParser();
        directionsList = parser.parseTime(s);
        duration=directionsList.get("duration");
        distance=directionsList.get("distance");
        Log.e("dist",distance+" abc");
        MarkerOptions markerOptions=new MarkerOptions();
        markerOptions.snippet("Distance="+distance+"Duration"+duration);
        markerOptions.position(latLng);
        mMap.addMarker(markerOptions);
    }
}
