package com.example.appcontroller;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class reclamationfragment extends Fragment {

    @Nullable
    EditText reclamation;
    Button envoyer;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reclamationfragment, container, false);
        reclamation=(EditText)view.findViewById(R.id.reclamation);
        envoyer =(Button) view.findViewById(R.id.button);
        envoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (reclamation.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), " ajouter votre réclamation !!", Toast.LENGTH_LONG).show();
                } else {
                        String url = "http://51.254.212.179:8082/rapportreclamation/add";
                        new HttpPost().execute(url);
                        Toast.makeText(getContext(), "  votre réclamation est envoyée avec succés  !!", Toast.LENGTH_LONG).show();

                    }

                }

        });



        return view;
    }

    public class HttpPost extends AsyncTask<String, String, HttpURLConnection> {



        protected HttpURLConnection doInBackground(String... args) {


                try {
                    URL url = new URL("http://51.254.212.179:8082/rapportreclamation/add");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.connect();
                    Date aujourdhui = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    dateFormat.format(aujourdhui);
                    SimpleDateFormat dateformat = new SimpleDateFormat(" HH:mm");
                    dateformat.format(aujourdhui);
                    JSONObject jsonParam = new JSONObject();
                    SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getContext());
                    String s2 = sh.getString("matriculecontroleur", "matricule");
                    String s5 =sh.getString("nomcontroleur","nom");
                    String s1=sh.getString("societe","societe");
                    jsonParam.put("type","reclamation");
                    jsonParam.put("societe",s1);
                    jsonParam.put("nom",s5);
                    jsonParam.put("matricule", s2);
                    jsonParam.put("dateaujourdhui",dateFormat.format(aujourdhui));
                    jsonParam.put("heureaujourdhui",dateformat.format(aujourdhui));
                    jsonParam.put("msg", reclamation.getText().toString());
                    jsonParam.put("statusreclamation","non lu");


                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    os.writeBytes(jsonParam.toString());

                    os.flush();
                    os.close();

                    Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                    Log.i("MSG", conn.getResponseMessage());

                    conn.disconnect();



                } catch (Exception e) {

                }

            return null;
        }


    }
}







