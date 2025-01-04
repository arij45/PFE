package com.example.appcontroller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.app.AlarmManager;
import android.app.FragmentTransaction;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;
import android.os.Handler;
import android.widget.Toast;

import static android.content.Context.ALARM_SERVICE;
import static android.content.Context.LOCATION_SERVICE;


public class boite_reception extends  Fragment  {


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 101:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                } else {
                    //not granted
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
    }

    ListView listView;
    TextView alerte;
    TextView Alerte;
    Location location;
    Geocoder geocoder;

    @RequiresApi(api = Build.VERSION_CODES.P)
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_boite_reception, container, false);
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getContext());
        String s2 = sh.getString("matriculecontroleur", "matricule");
        String url = "http://51.254.212.179:8082/planning/findBymatricule?matriculecontroleur=" + s2;
        super.onCreate(savedInstanceState);
        listView = (ListView) view.findViewById(R.id.listView);
        alerte = (TextView) view.findViewById(R.id.alerte);
        Alerte = (TextView) view.findViewById(R.id.Alerte);
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }
        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        geocoder = new Geocoder(getContext());
        System.out.println("locc =" + location.getTime());
        new arij().execute(url);




        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    new arij().execute(url);
                    handler.postDelayed(this, 5000);//60 second delay
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
        };




        handler.postDelayed(runnable, 5000);
        return view;


    }




    public class arij extends AsyncTask<String, String, String> {



        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getContext());
        String s2;

        @Override

        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            s2 = sh.getString("matriculecontroleur", "matricule");
            String s = "http://51.254.212.179:8082/planning/findBymatricule?matriculecontroleur=" + s2;
            String content = HttpULRConnect.getData(s);
            return content;

        }


        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        protected void onPostExecute(String s) {

            s2 = sh.getString("matriculecontroleur", "matricule");
            boolean testBolean = false;
            boolean TestBolean = false;

            try {

                JSONArray ar = new JSONArray(s);
                if (ar.length() == 0) {
                    alerte.setVisibility(View.VISIBLE);

                } else {


                    List<planning> listPlanning = new ArrayList<>();
                    for (int i = 0; i < ar.length(); i++) {
                        planning planningModel = new planning();
                        JSONObject jsonobject = ar.getJSONObject(i);
                        String matriculebinome = jsonobject.getString("matriculebinome");
                        Log.e("message", matriculebinome);
                        String matriculecontroleur = jsonobject.getString("matriculecontroleur");
                        Log.e("message", matriculecontroleur);
                        String nom = jsonobject.getString("nombinome");
                        String nomcontroleur = jsonobject.getString("nomcontroleur");
                        String bus = jsonobject.getString("bus");
                        String ligne = jsonobject.getString("ligne");
                        Log.e("ligne", ligne);
                        String station = jsonobject.getString("station");
                        String heure = jsonobject.getString("heure");
                        String parametre = jsonobject.getString("parametre");
                        String date = jsonobject.getString("date");
                        String numero = jsonobject.getString("numero");
                        Date aujourdhui = new Date();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Log.e("message ", dateFormat.format(aujourdhui));
                        Log.e("location", String.valueOf(location.getTime()));
                        Date date3 = dateFormat.parse(date);
                        Calendar cal2 = Calendar.getInstance();
                        cal2.setTime(date3);
                        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
                        dateFormat.format(cal2.getTime());
                        Log.e("message planning", dateFormat.format(cal2.getTime()));
                        if (dateFormat.format(location.getTime()).equals(dateFormat.format(aujourdhui))) {
                            if (dateFormat.format(aujourdhui).equals(dateFormat.format(cal2.getTime()))) {

                                String valeur = "5:00";
                                Date date4 = null;
                                try {
                                    date4 = formatter.parse(valeur);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                cal2.setTime(date4);
                                formatter.format(cal2.getTime());


                                if ((parametre.equals("0"))) {


                                    if (formatter.format(aujourdhui).compareTo(formatter.format(cal2.getTime())) >= 0) {
                                        if (s2.equals(matriculecontroleur)) {
                                            planningModel.setMatriculebinome(matriculebinome);
                                            planningModel.setNombinome(nom);
                                        } else {
                                            planningModel.setMatriculebinome(matriculecontroleur);
                                            planningModel.setNombinome(nomcontroleur);
                                        }
                                        if (station.equals("")) {
                                            planningModel.setStation("null");
                                        } else {
                                            planningModel.setStation(station);
                                        }
                                        planningModel.setBus(bus);
                                        planningModel.setHeure(heure);
                                        planningModel.setLigne(ligne);
                                        planningModel.setNumero(numero);
                                        listPlanning.add(planningModel);
                                        testBolean = true;
                                    }

                                    if (formatter.format(aujourdhui).compareTo(formatter.format(cal2.getTime())) == 0) {
                                        Intent intent = new Intent(getContext(), ReminderBrodcast.class);
                                        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 0, intent, 0);
                                        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
                                        long timeAtButtonclick = System.currentTimeMillis();
                                        long tenSecondsInMillis = 1000;
                                        alarmManager.set(AlarmManager.RTC_WAKEUP, timeAtButtonclick + tenSecondsInMillis, pendingIntent);
                                        addnotification();
                                    }


                                } else {
                                    int Parametre = Integer.parseInt(parametre);
                                    System.out.println(Parametre);
                                    Date date2 = null;
                                    try {
                                        date2 = formatter.parse(heure);
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }


                                    cal2.setTime(date2);
                                    cal2.add(Calendar.HOUR_OF_DAY, -Parametre);
                                    System.out.println("heureString =  " + heure);
                                    System.out.println("heure s7i7 " + formatter.format(cal2.getTime()));
                                    System.out.println(formatter.format(aujourdhui));


                                    if (formatter.format(aujourdhui).compareTo(formatter.format(cal2.getTime())) >= 0) {

                                        if (s2.equals(matriculecontroleur)) {
                                            planningModel.setMatriculebinome(matriculebinome);
                                            planningModel.setNombinome(nom);
                                        } else {
                                            planningModel.setNombinome(nomcontroleur);
                                            planningModel.setMatriculebinome(matriculecontroleur);
                                        }
                                        if (station.equals("")) {
                                            planningModel.setStation("null");
                                        } else {
                                            planningModel.setStation(station);
                                        }

                                        planningModel.setBus(bus);
                                        planningModel.setHeure(heure);
                                        planningModel.setLigne(ligne);
                                        planningModel.setStation(station);
                                        planningModel.setNumero(numero);
                                        listPlanning.add(planningModel);
                                        testBolean = true;

                                    }

                                    if (formatter.format(aujourdhui).compareTo(formatter.format(cal2.getTime())) == 0) {
                                        Intent intent = new Intent(getContext(), ReminderBrodcast.class);
                                        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 0, intent, 0);
                                        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
                                        long timeAtButtonclick = System.currentTimeMillis();
                                        long tenSecondsInMillis = 1000;
                                        alarmManager.set(AlarmManager.RTC_WAKEUP, timeAtButtonclick + tenSecondsInMillis, pendingIntent);
                                        addnotification();


                                    }


                                }


                            }

                            TestBolean = true;
                        }

                    }


                    if (!TestBolean) {
                        Alerte.setVisibility(View.VISIBLE);
                    } else {
                        Alerte.setVisibility(View.INVISIBLE);
                        if (!testBolean) {
                            alerte.setVisibility(View.VISIBLE);

                        } else {
                            alerte.setVisibility(View.INVISIBLE);
                        }

                    }


                    planningListAdapter adapter = new planningListAdapter(getContext(), listPlanning);
                    listView.setAdapter(adapter);


                }
            }

            catch (JSONException | ParseException e) {
                e.printStackTrace();
            }
        }


    }

    public void addnotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "LemubitReminderChannel";
            String description = "channel for lemubit Reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notifyLemubit", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }


    }
}


















