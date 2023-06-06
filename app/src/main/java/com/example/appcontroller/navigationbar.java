package com.example.appcontroller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;

public class navigationbar extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigationbar);
        drawerLayout = findViewById(R.id.nav_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String s1;
        s1=sh.getString("societe","societe");
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(s1);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container, new boite_reception());
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_draw_open, R.string.navigation_draw_open);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container, new boite_reception()).commit();
            navigationView.setCheckedItem(R.id.nav_acceuil);
        }
        NavigationView nav = findViewById(R.id.navigation_view);
        View header = nav.getHeaderView(0);
        TextView v = header.findViewById(R.id.iv_user);
        String s2  ;
        s2 =sh.getString("nomcontroleur","nom");
        v.setText(s2);
        Log.e("message", String.valueOf(v));



        Menu menu = navigationView.getMenu();

        MenuItem tools= menu.findItem(R.id.comminuquer);
        SpannableString s = new SpannableString(tools.getTitle());
        s.setSpan(new TextAppearanceSpan(this, R.style.TextAppearance44), 0, s.length(), 0);
        tools.setTitle(s);
        navigationView.setNavigationItemSelectedListener(this);





    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case R.id.nav_acceuil:

                getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container, new boite_reception()).commit();
                Toast.makeText(navigationbar.this, " Bienvenue !!", Toast.LENGTH_LONG).show();
                break;



            case R.id.nav_deconnexion:
                AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.AlertDialogStyle);
                builder.setTitle(" Confirmation de déconnexion ");
                builder.setMessage(" Voulez vous se déconnecter ? ")
                        .setCancelable(false)
                        .setPositiveButton("OUI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();

                            }
                        })
                        .setNegativeButton("NON", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            case R.id.nav_propos:
                getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container, new aproposfragment()).commit();
                Toast.makeText(navigationbar.this, " Bienvenue !!", Toast.LENGTH_LONG).show();
                break;

            case R.id.nav_CodeQR:
                getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container, new CodeQR()).commit();
                Toast.makeText(navigationbar.this, " Bienvenue !!", Toast.LENGTH_LONG).show();
                break;

            case R.id.nav_reclamation:
                getSupportFragmentManager().beginTransaction().replace(R.id.Fragment_container, new reclamationfragment()).commit();
                Toast.makeText(navigationbar.this, " Bienvenue !!", Toast.LENGTH_LONG).show();
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}
