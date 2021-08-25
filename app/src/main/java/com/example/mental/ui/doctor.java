package com.example.mental.ui;

import static androidx.navigation.fragment.NavHostFragment.findNavController;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.streamchatdemo.R;
import com.example.streamchatdemo.ui.channel.ChannelFragment;
import com.example.mental.doctor.patientCases;
import com.google.android.material.navigation.NavigationView;

import io.getstream.chat.android.client.ChatClient;
import io.getstream.chat.android.client.logger.ChatLogLevel;
import io.getstream.chat.android.livedata.ChatDomain;

public class doctor extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,  com.example.mental.doctor.profileDoctor.OnFragmentInteractionListener {

    private AppBarConfiguration mAppBarConfiguration;
    //private ActivityDoctorBinding binding;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            setContentView(R.layout.activity_doctor);            // ... rest of body of onCreateView() ...
        } catch (Exception e) {
            Log.e("route", "onCreateView", e);
            throw e;
        }
        /*binding = ActivityDoctorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());*/

        Toolbar toolbar = findViewById(R.id.toolbar_doctor);
        setSupportActionBar(toolbar);

        /*
           <fragment
            android:id="@+id/nav_host_fragment_content_doctor"
            android:name="androidx.navigation.fragment.NavHostFragment"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:defaultNavHost="true"
            app:navGraph="@navigation/mobile_navigation2" />
         */
/*
        setSupportActionBar(binding.appBarDoctor.toolbar);
        binding.appBarDoctor.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        //DrawerLayout drawer = binding.drawerLayout;
        //NavigationView navigationView = binding.navView;

        drawer = findViewById(R.id.drawer_layout_doctor);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new com.example.mental.doctor.profileDoctor()).commit();
            navigationView.setCheckedItem(R.id.profile_doctor);
        }

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
     /*   mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.profile_doctor, R.id.patients_doctor, R.id.chat_doctor, R.id.sign_out_doctor)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.fragment_container);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.doctor, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.fragment_container);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @SuppressLint("WrongConstant")
    @Override
    public boolean onNavigationItemSelected( MenuItem item) {
        //getChildFragmentManager
        switch (item.getItemId()) {
            case R.id.profile_doctor:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new com.example.mental.doctor.profileDoctor()).commit();
                break;
            case R.id.patients_doctor:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new com.example.mental.doctor.patientsDoctor()).commit();
                break;
            case R.id.cases_doctor:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new patientCases()).commit();
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    private void goAway() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you leaving?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                //SharedPreferences.Editor.clear();
                startActivity(new Intent(doctor.this, MainActivity.class));
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
        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                //new ChannelFragment()).commit();
        startActivity(new Intent(this, com.example.streamchatdemo.ui.MainActivity.class));

    }


    public void onFragmentInteraction(Uri uri) {

    }

}