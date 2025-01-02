package com.example.appcontroller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Rapport extends AppCompatActivity {
    CheckBox pasasignaler;
    CheckBox problemeavecvoyageur;
    CheckBox problemeaveccontroleur;
    CheckBox problemeavecreceveur;
    Button valider;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rapport);
        pasasignaler = (CheckBox) findViewById(R.id.rienasignaler);
        problemeavecvoyageur = (CheckBox) findViewById(R.id.problemevoyageur);
        problemeaveccontroleur = (CheckBox) findViewById(R.id.problemecontroleur);
        problemeavecreceveur = (CheckBox) findViewById(R.id.problemereceveur);
        valider = (Button) findViewById(R.id.valider);



        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String url = "http://51.254.212.179:8082/rapportreclamation/add";
                    new HttpPost().execute(url);
            }
        });

    }

    public class HttpPost extends AsyncTask<String, String, HttpURLConnection> {



        protected HttpURLConnection doInBackground(String... args) {

            if(((problemeaveccontroleur.isChecked())&&(problemeavecreceveur.isChecked())&&(problemeavecvoyageur.isChecked())&&(pasasignaler.isChecked()))||((pasasignaler.isChecked())&&(problemeaveccontroleur.isChecked()))||((pasasignaler.isChecked())&&(problemeavecvoyageur.isChecked()))||((pasasignaler.isChecked())&&(problemeavecreceveur.isChecked()))) {

                runOnUiThread(new Runnable() {

                    public void run() {

                        Toast.makeText(getApplicationContext(), "Choisir les bonnes réponses !!", Toast.LENGTH_SHORT).show();

                    }
                });
            }

            else {


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
                    SimpleDateFormat dateformat = new SimpleDateFormat("HH:mm");
                    dateformat.format(aujourdhui);
                    JSONObject jsonParam = new JSONObject();
                    SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    String s2 = sh.getString("matriculecontroleur", "matricule");
                    String s3 = sh.getString("matriculebinome","matriculebinome");
                    String s4 =sh.getString("heure","heure");
                    String s5 =sh.getString("nomcontroleur","nom");
                    String s1=sh.getString("societe","societe");

                    if ((problemeaveccontroleur.isChecked()) && (problemeavecreceveur.isChecked()) && (problemeavecvoyageur.isChecked())) {

                        valider.setBackgroundColor(getResources().getColor(R.color.orange));
                        jsonParam.put("type","rapport");
                        jsonParam.put("societe",s1);
                        jsonParam.put("nom",s5);
                        jsonParam.put("matricule",s2);
                        jsonParam.put("msg", " Probleme avec controleur , voyageur , receveur");
                        jsonParam.put("dateaujourdhui",dateFormat.format(aujourdhui));
                        jsonParam.put("heureaujourdhui",dateformat.format(aujourdhui));
                        jsonParam.put("matriculecontroleur", s2);
                        jsonParam.put("matriculebinome", s3);
                        jsonParam.put("heure", s4);
                        jsonParam.put("statusRapport","non lu");
                        runOnUiThread(new Runnable() {

                            public void run() {

                                Toast.makeText(getApplicationContext(), "Votre rapport a été envoyé avec succés !!", Toast.LENGTH_SHORT).show();

                            }
                        });


                    }
                    else if ((problemeaveccontroleur.isChecked()) && (problemeavecreceveur.isChecked())) {

                        valider.setBackgroundColor(getResources().getColor(R.color.orange));
                        jsonParam.put("type","rapport");
                        jsonParam.put("societe",s1);
                        jsonParam.put("nom",s5);
                        jsonParam.put("matricule",s2);
                        jsonParam.put("msg", " Probleme avec controleur , receveur");
                        jsonParam.put("dateaujourdhui",dateFormat.format(aujourdhui));
                        jsonParam.put("heureaujourdhui",dateformat.format(aujourdhui));
                        jsonParam.put("matriculecontroleur", s2);
                        jsonParam.put("matriculebinome", s3);
                        jsonParam.put("heure", s4);
                        jsonParam.put("statusRapport","non lu");
                        runOnUiThread(new Runnable() {

                            public void run() {

                                Toast.makeText(getApplicationContext(), "Votre rapport a été envoyé avec succés !!", Toast.LENGTH_SHORT).show();

                            }
                        });


                    }
                    else if ((problemeaveccontroleur.isChecked()) && (problemeavecvoyageur.isChecked())) {

                        valider.setBackgroundColor(getResources().getColor(R.color.orange));
                        jsonParam.put("type","rapport");
                        jsonParam.put("societe",s1);
                        jsonParam.put("nom",s5);
                        jsonParam.put("matricule",s2);
                        jsonParam.put("msg", " Probleme avec controleur , voyageur ");
                        jsonParam.put("dateaujourdhui",dateFormat.format(aujourdhui));
                        jsonParam.put("heureaujourdhui",dateformat.format(aujourdhui));
                        jsonParam.put("matriculecontroleur", s2);
                        jsonParam.put("matriculebinome", s3);
                        jsonParam.put("heure", s4);
                        jsonParam.put("statusRapport","non lu");
                        runOnUiThread(new Runnable() {

                            public void run() {

                                Toast.makeText(getApplicationContext(), "Votre rapport a été envoyé avec succés !!", Toast.LENGTH_SHORT).show();

                            }
                        });


                    }
                    else if (problemeavecvoyageur.isChecked() && (problemeavecreceveur.isChecked())) {

                        valider.setBackgroundColor(getResources().getColor(R.color.orange));
                        jsonParam.put("type","rapport");
                        jsonParam.put("societe",s1);
                        jsonParam.put("nom",s5);
                        jsonParam.put("matricule",s2);
                        jsonParam.put("msg", " Probleme avec voyageur , receveur");
                        jsonParam.put("dateaujourdhui",dateFormat.format(aujourdhui));
                        jsonParam.put("heureaujourdhui",dateformat.format(aujourdhui));
                        jsonParam.put("matriculecontroleur", s2);
                        jsonParam.put("matriculebinome", s3);
                        jsonParam.put("heure", s4);
                        jsonParam.put("statusRapport","non lu");
                        runOnUiThread(new Runnable() {

                            public void run() {

                                Toast.makeText(getApplicationContext(), "Votre rapport a été envoyé avec succés !!", Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                    else if (pasasignaler.isChecked()) {

                        valider.setBackgroundColor(getResources().getColor(R.color.orange));
                        jsonParam.put("type","rapport");
                        jsonParam.put("societe",s1);
                        jsonParam.put("nom",s5);
                        jsonParam.put("matricule",s2);
                        jsonParam.put("msg", "Rien à signaler");
                        jsonParam.put("dateaujourdhui",dateFormat.format(aujourdhui));
                        jsonParam.put("heureaujourdhui",dateformat.format(aujourdhui));
                        jsonParam.put("matriculecontroleur", s2);
                        jsonParam.put("matriculebinome", s3);
                        jsonParam.put("heure", s4);
                        jsonParam.put("statusRapport","non lu");
                        runOnUiThread(new Runnable() {

                            public void run() {

                                Toast.makeText(getApplicationContext(), "Votre rapport a été envoyé avec succés !!", Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                    else if (problemeaveccontroleur.isChecked()) {

                        valider.setBackgroundColor(getResources().getColor(R.color.orange));
                        jsonParam.put("type","rapport");
                        jsonParam.put("societe",s1);
                        jsonParam.put("nom",s5);
                        jsonParam.put("matricule",s2);
                        jsonParam.put("msg", " Probleme avec controleur ");
                        jsonParam.put("dateaujourdhui",dateFormat.format(aujourdhui));
                        jsonParam.put("heureaujourdhui",dateformat.format(aujourdhui));
                        jsonParam.put("matriculecontroleur", s2);
                        jsonParam.put("matriculebinome", s3);
                        jsonParam.put("heure", s4);
                        jsonParam.put("statusRapport","non lu");
                        runOnUiThread(new Runnable() {

                            public void run() {

                                Toast.makeText(getApplicationContext(), "Votre rapport a été envoyé avec succés !!", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                    else if (problemeavecvoyageur.isChecked()) {

                        valider.setBackgroundColor(getResources().getColor(R.color.orange));
                        jsonParam.put("type","rapport");
                        jsonParam.put("societe",s1);
                        jsonParam.put("nom",s5);
                        jsonParam.put("matricule",s2);
                        jsonParam.put("msg", " Probleme avec voyageur");
                        jsonParam.put("dateaujourdhui",dateFormat.format(aujourdhui));
                        jsonParam.put("heureaujourdhui",dateformat.format(aujourdhui));
                        jsonParam.put("matriculecontroleur", s2);
                        jsonParam.put("matriculebinome", s3);
                        jsonParam.put("heure", s4);
                        jsonParam.put("statusRapport","non lu");
                        runOnUiThread(new Runnable() {

                            public void run() {

                                Toast.makeText(getApplicationContext(), "Votre rapport a été envoyé avec succés !!", Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                    else if (problemeavecreceveur.isChecked()) {

                        valider.setBackgroundColor(getResources().getColor(R.color.orange));
                        jsonParam.put("type","rapport");
                        jsonParam.put("societe",s1);
                        jsonParam.put("nom",s5);
                        jsonParam.put("matricule",s2);
                        jsonParam.put("msg", " Probleme avec receveur");
                        jsonParam.put("dateaujourdhui",dateFormat.format(aujourdhui));
                        jsonParam.put("heureaujourdhui",dateformat.format(aujourdhui));
                        jsonParam.put("matriculecontroleur", s2);
                        jsonParam.put("matriculebinome", s3);
                        jsonParam.put("heure", s4);
                        jsonParam.put("statusRapport","non lu");
                        runOnUiThread(new Runnable() {

                            public void run() {

                                Toast.makeText(getApplicationContext(), "Votre rapport a été envoyé avec succés !!", Toast.LENGTH_SHORT).show();

                            }
                        });


                    }




                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    os.writeBytes(jsonParam.toString());

                    os.flush();
                    os.close();

                    Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                    Log.i("MSG", conn.getResponseMessage());

                    conn.disconnect();


                } catch (Exception e) {

                }
            }




                return null;
            }


    }
}
