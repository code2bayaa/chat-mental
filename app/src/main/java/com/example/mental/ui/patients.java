package com.example.mental.ui;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.streamchatdemo.R;
import com.example.mental.patients.doctorList;
import com.example.mental.patients.patientFiles;
import com.example.mental.patients.profilePatient;
import com.google.android.material.navigation.NavigationView;

import io.getstream.chat.android.client.ChatClient;
import io.getstream.chat.android.client.logger.ChatLogLevel;
import io.getstream.chat.android.livedata.ChatDomain;

public class patients extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            setContentView(R.layout.activity_patients);            // ... rest of body of onCreateView() ...
        } catch (Exception e) {
            Log.e("route", "onCreateView", e);
            throw e;
        }

        Toolbar toolbar = findViewById(R.id.toolbar_patient);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout_patient);
        NavigationView navigationView = findViewById(R.id.nav_view_patient);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_patient,
                    new profilePatient()).commit();
            navigationView.setCheckedItem(R.id.profile_patient);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.patient, menu);
        return true;
    }

    @SuppressLint("WrongConstant")
    @Override
    public boolean onNavigationItemSelected( MenuItem item) {
        //getChildFragmentManager
        switch (item.getItemId()) {
            case R.id.profile_patient:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_patient,
                        new profilePatient()).commit();
                break;
            case R.id.doctors_list:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_patient,
                        new doctorList()).commit();
                break;
            case R.id.medical_patient:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_patient,
                        new patientFiles()).commit();
                break;
            case R.id.chat_portal:
                setChat();
                break;
            case R.id.sign_out:
                goAway();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    private void goAway() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you leaving?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                //SharedPreferences.Editor.clear();
                startActivity(new Intent(patients.this, MainActivity.class));
                Toast.makeText(getApplicationContext(), "Logged out successfully", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setTitle("Logout?");
        builder.create().show();

    }
    private void setChat() {

        new ChatDomain.Builder(new ChatClient.Builder(getString(R.string.api_key), this).logLevel(ChatLogLevel.ALL).build(), this).build();
        startActivity(new Intent(this, com.example.streamchatdemo.ui.MainActivity.class));

    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


}