package com.example.mental.ui;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.streamchatdemo.R;
import com.example.mental.homePage;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText mEmail, mPassword;
    public static Bundle userSession = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmail = findViewById(R.id.email_login);
        mPassword = findViewById(R.id.password_login);

        TextView mLogIn;
        Button m_login_button,homeButton;

        mLogIn = findViewById(R.id.logIn_sign);
        m_login_button = findViewById(R.id.logIn_button);
        homeButton = findViewById(R.id.home_button);

        //setWallpaper();

        mLogIn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, signup.class)));
        homeButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, homePage.class)));

        m_login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                //intent.putExtra("email",mEmail.getText().toString());
                //startActivity(intent);
                if((!mEmail.getText().toString().isEmpty()) && (!mPassword.getText().toString().isEmpty())) {
                    logIn(mEmail.getText().toString(), mPassword.getText().toString());
                } else {
                    if(mEmail.getText().toString().isEmpty()) {
                        Toast.makeText(MainActivity.this, "Please input your email", Toast.LENGTH_SHORT).show();
                    }

                    if(mPassword.getText().toString().isEmpty()) {
                        Toast.makeText(MainActivity.this, "Please input your password", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }

    private void setWallpaper() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.wallpaper);
        WallpaperManager manager = WallpaperManager.getInstance(getApplicationContext());
        try{
            manager.setBitmap(bitmap);
            Toast.makeText(this, "Wallpaper set!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
        }
    }

    private void logIn( final String email, final String password) {

        //mProgress.setVisibility(View.VISIBLE);
        // Initializing Request queue
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://192.168.0.22/mental/login.php?email="+email+"&password="+password, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    Toast.makeText(MainActivity.this,response,Toast.LENGTH_SHORT).show();
                    JSONObject jsonObject = new JSONObject(response);

                    String message = jsonObject.getString("message");
                    String user = jsonObject.getString("user");

                    Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();

                    if (message.equals("Login Success")) {
                        //mProgress.setVisibility(View.GONE);
                        // Finish
                        //finish();
                        // Start activity dashboard
                        SharedPreferences pref = getSharedPreferences("user", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = pref.edit();
                        edit.putString("user", mEmail.getText().toString());
                        edit.putString("special", user);

// Commit the changes
                        edit.commit();
                        if(user.equals("Patient")) {
                            startActivity(new Intent(MainActivity.this, patients.class));
                            user = "Welcome "+user;
                        }
                        if(user.equals("Doctor")){
                            startActivity(new Intent(MainActivity.this, doctor.class));
                            user = "Welcome "+user;
                        }
                        Toast.makeText(MainActivity.this,user,Toast.LENGTH_SHORT).show();

                    }

                } catch (JSONException e) {

                    //mProgress.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this,e.toString(),Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //mProgress.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("email",email);
                params.put("password",password);

                return params;
            }
        };

        stringRequest.setShouldCache(false);
        queue.add(stringRequest);
    }
}