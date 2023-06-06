package com.example.appcontroller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.infowindow.InfoWindow;

public class OsmdroidMap extends AppCompatActivity {
    private MapView map;
    IMapController mapController;
    private Handler mHandler;
    private long mInterval =  5000 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        setContentView(R.layout.activity_osmdroid_map);
        map = findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        GeoPoint startpoint = new GeoPoint(33.886917, 9.537499);
        mapController = map.getController();
        mapController.setZoom(8.0);
        mapController.setCenter(startpoint);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);
        SharedPreferences sh = android.preference.PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String s1=sh.getString("societe","societe");
        String url = "http://51.254.212.179:8080/service/allBusFromServiceFinal?Nom="+s1;
        new map().execute(url);
        mHandler = new Handler();
        startRepeatingTask();
    }

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                SharedPreferences sh = android.preference.PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String s1=sh.getString("societe","societe");


                String url = "http://51.254.212.179:8080/service/allBusFromServiceFinal?Nom="+s1;
                new map().execute(url);
            } finally {
                mHandler.postDelayed(mStatusChecker, mInterval);
            }
        }
    };




   void startRepeatingTask() {
        mStatusChecker.run();
    }

    public class map extends AsyncTask<String, String, String>{

        SharedPreferences sh = android.preference.PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String s2;

        @Override

        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String s1=sh.getString("societe","societe");
            String s = "http://51.254.212.179:8080/service/allBusFromServiceFinal?Nom="+s1;
            String content = HttpULRConnect.getData(s);
            return content;
        }

        @Override
        protected void onPostExecute(String s) {
            if (map != null)

            {

                map.getOverlayManager().clear();
                InfoWindow.closeAllInfoWindowsOn(map);
                map.invalidate();
            }
            try {

                JSONArray ar = new JSONArray(s);
                for (int i = 0; i < ar.length(); i++) {
                    JSONObject jsonobject = ar.getJSONObject(i);
                    String correspondance = jsonobject.getString("correspendance");
                    s2 = sh.getString("ligne", "ligne");
                    if(correspondance.equals(s2)) {


                        JSONObject localisation = jsonobject.getJSONObject("localisation");
                        String lat = localisation.getString("x");
                        String lon = localisation.getString("y");
                        Log.e("x", String.valueOf(lat));
                        Log.e("y", String.valueOf(lon));
                        double latt = Double.parseDouble(lat);
                        double lonn = Double.parseDouble(lon);


                        if (lat.equals("NaN") && (lon.equals("NaN"))) {
                            JSONObject localisationancien = jsonobject.getJSONObject("localisationancien");
                            String latan = localisationancien.getString("x");
                            String lonan = localisationancien.getString("y");
                            Log.e("x", String.valueOf(latan));
                            Log.e("y", String.valueOf(lonan));
                            double lattan = Double.parseDouble(latan);
                            double lonnan = Double.parseDouble(lonan);
                            Marker marker = new Marker(map);
                            marker = new Marker(map);
                            marker.setPosition(new GeoPoint(lattan, lonnan));
                            marker.setTitle(jsonobject.getString("correspendance")+ System.getProperty("line.separator")+ jsonobject.getString("orfr")+ " -->"+ jsonobject.getString("desfr"));
                            map.getOverlays().add(marker);
                            map.invalidate();
                            map.getController().animateTo(new GeoPoint(latt,lonn),18.0,120L);
                            marker.showInfoWindow();
                            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                            marker.setIcon(getResources().getDrawable(R.drawable.busorange));
                            marker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
                                @Override
                                public boolean onMarkerClick(Marker marker, MapView mapView) {
                                    map.getController().animateTo(marker.getPosition());
                                    map.getController().setZoom(18);
                                    marker.showInfoWindow();
                                    return false;
                                }
                            });

                        } else {
                            Marker marker = new Marker(map);
                            marker = new Marker(map);
                            marker.setPosition(new GeoPoint(latt, lonn));

                            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                            marker.setIcon(getResources().getDrawable(R.drawable.busvert));
                            marker.setTitle(jsonobject.getString("correspendance")+ System.getProperty("line.separator")+ jsonobject.getString("orfr")+ " -->"+ jsonobject.getString("desfr"));

                            marker.setSnippet("Ligne " + correspondance);
                            map.getOverlays().add(marker);
                            map.invalidate();
                            map.getController().animateTo(new GeoPoint(latt,lonn),18.0,120L);
                          marker.showInfoWindow();

                            marker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
                                @Override
                                public boolean onMarkerClick(Marker marker, MapView mapView) {
                                    map.getController().setCenter(marker.getPosition());
                                    map.getController().setZoom(18);
                                    marker.showInfoWindow();
                                    return false;
                                }
                            });

                        }
                    }

                    if(correspondance.equals(s2)){

                        JSONArray jArray1 = jsonobject.getJSONArray("array_lat_opendata");
                        JSONArray jArray2 = jsonobject.getJSONArray("array_lng_opendata");
                        JSONArray jArray3 = jsonobject.getJSONArray("array_nom_fr_opendata");
                        JSONArray jArray4 = jsonobject.getJSONArray("array_nom_ar_opendata");

                        Log.i("latitude", String.valueOf(jArray1));
                        Log.i("longitude", String.valueOf(jArray2));
                        Log.i("nomfr", String.valueOf(jArray3));
                        Log.i("nomar", String.valueOf(jArray4));


                        for ( int j = 0; j < 100; j++) {
                            Log.i("Valuelatitude", jArray1.getString(j));
                            double latitude = Double.parseDouble(jArray1.getString(j));
                            double longitude = Double.parseDouble(jArray2.getString(j));
                            String valeurfr = jArray3.getString(j);
                            String valeurar = jArray4.getString(j);
                            Marker marker = new Marker(map);
                            marker = new Marker(map);
                            marker.setPosition(new GeoPoint(latitude, longitude));
                            map.getOverlays().add(marker);
                            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                            marker.setIcon(getResources().getDrawable(R.drawable.station));
                            marker.setTitle("Station");
                            marker.setSnippet(valeurfr);
                            marker.setSubDescription(valeurar);
                        }






                    }

                    }









                } catch (JSONException e) {
                e.printStackTrace();
            }




        }
    }


   @Override
    public void onPause(){
    super.onPause();
    map.onPause();
    }
    @Override
    public void onResume(){
        super.onResume();
        map.onResume();
    }
}

