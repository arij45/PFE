package com.example.appcontroller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import android.os.Handler;


public class MainActivity extends AppCompatActivity {
    private EditText codesecret1;
    private Button eLogin;
    private int counter = 5;
    private ProgressBar progressBar;
        @Override


        protected void onCreate(Bundle savedInstanceState) {


            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            codesecret1 = findViewById(R.id.at_password);
            eLogin = findViewById(R.id.btn_login);
            progressBar =(ProgressBar)findViewById(R.id.progressBar);
            codesecret1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  ////  InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

                //    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            });
            eLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   String url= "http://51.254.212.179:8082/controleur/findBycodesecret?code_secret="+codesecret1.getText().toString();
                    new TEST().execute(url);
                    progressBar.setVisibility(View.VISIBLE);

                }


            });

        }

        public class TEST extends AsyncTask<String, String, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }


            @Override

            protected String doInBackground(String... strings) {
               String s = "http://51.254.212.179:8082/controleur/findBycodesecret?code_secret="+codesecret1.getText().toString();
                String content = HttpULRConnect.getData(s);

                return content;

            }

            @Override
            protected void onPostExecute(String s) {

                try {

                    JSONObject root = new JSONObject(s);
                    String matricule = root.getString("matriculecontroleur");
                    String nom = root.getString("nom");
                    String codesecret = root.getString("codesecret");
                    String societe = root.getString("societe");
                    SharedPreferences.Editor myEdit = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
                    myEdit.putString("matriculecontroleur", matricule);
                    myEdit.putString("nomcontroleur",nom);
                    myEdit.putString("societe",societe);
                    myEdit.apply();


                        if ((codesecret.equals((codesecret1.getText().toString().trim())))) {
                            Toast.makeText(MainActivity.this, " Bienvenue !! ", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(MainActivity.this, navigationbar.class));
                        } else {
                            Toast.makeText(MainActivity.this, " coordonnée incorrecte, réessayez plus tard !!", Toast.LENGTH_LONG).show();

                        }


                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, " coordonnée incorrecte, réessayez encore !!", Toast.LENGTH_LONG).show();
                         counter--;
                            if (counter == 0) {
                                eLogin.setEnabled(false);
                                Toast.makeText(MainActivity.this, " Vous avez utilisé toutes vos tentatives, réessayez plus tard !!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(MainActivity.this, " coordonnée incorrecte, réessayez encore !!", Toast.LENGTH_LONG).show();
                            }

                }


            }

        }
    }



