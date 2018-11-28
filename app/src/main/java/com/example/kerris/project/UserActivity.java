package com.example.kerris.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {

    TextView tex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        tex = (TextView) findViewById(R.id.text);
        tex.setText("Welcome " + MainActivity.u + " ! You are logged as " + MainActivity.rol);
    }

}