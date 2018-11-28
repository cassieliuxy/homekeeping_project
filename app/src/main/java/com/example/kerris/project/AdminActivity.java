package com.example.kerris.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminActivity extends AppCompatActivity {

    TextView tex;
    TextView hintad;
    EditText ser;
    EditText rat;
    Button addBtn, updateBtn, deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        hintad = (TextView) findViewById(R.id.hintadmin);
        ser = (EditText) findViewById(R.id.servicebox);
        rat = (EditText) findViewById(R.id.ratebox);
        tex = (TextView) findViewById(R.id.textadmin);
        tex.setText("Welcome " + MainActivity.u + "! You are logged as Administration");
        addBtn = (Button) findViewById(R.id.add);
        updateBtn = (Button) findViewById(R.id.update);
        deleteBtn = (Button) findViewById(R.id.delete);
    }


    public void newService(View view) {
        String servicename = ser.getText().toString().trim();
        String rate = rat.getText().toString().trim();

        Pattern p = Pattern.compile("[0-9]*");
        Matcher m = p.matcher(servicename);

        if(servicename.isEmpty() ||  m.matches()){
            if(rate.isEmpty()){
                hintad.setText("Invalid servicename and rate.");
            }
            else{
                hintad.setText("Invalid servicename.");
            }
        }else{
            if(rate.isEmpty()){
                hintad.setText("Invalid rate.");
            }else{
                // TODO: get from Database
                Servicedb servicedb = new Servicedb(this);
                Service service = servicedb.findService(servicename);
                if (service != null) {
                    hintad.setText("The service already exists. Please add again or change the rate click UPDATE.");
                    ser.setText("");
                    rat.setText("");
                } else {
                    // TODO: add to database
                    Service service1 = new Service(servicename, rate);
                    servicedb.addService(service1);
                    hintad.setText("Create service successfully.");
                }
            }
        }
    }

    public void editService(View view) {
        String servicename = ser.getText().toString().trim();
        String rate = rat.getText().toString().trim();

        if(servicename.isEmpty()){
            if(rate.isEmpty()){
                hintad.setText("Invalid servicename and rate.");
            }else{
                hintad.setText("Invalid servicename.");
            }
        }else {
            if (rate.isEmpty()) {
                hintad.setText("Invalid rate.");
            } else {
                // TODO: get from Database
                Servicedb servicedb = new Servicedb(this);
                Service service = servicedb.findService(servicename);
                if (service != null) {
                    servicedb.changeService(servicename, rate);
                    hintad.setText("Change rate successfully.");
                } else {
                    // TODO: add to database
                    hintad.setText("The service doesn't exists. Please click ADD.");
                }
            }
        }
    }

    public void removeService (View view) {
        String servicename = ser.getText().toString().trim();

        if(servicename.isEmpty()){
            hintad.setText("Please input servicename.");
        }else{
            // TODO: remove from database
            Servicedb servicedb = new Servicedb(this);
            boolean result = servicedb.deleteService(servicename);
            if (result) {
                hintad.setText("Delete service successfully.");
                ser.setText("");
                rat.setText("");
            }
            else {
                hintad.setText("Delete service unsuccessfully.");
            }
        }
    }

    public void viewService (View view) {
        Intent intent = new Intent(getApplicationContext(), ServiceActivity.class);
        startActivityForResult(intent, 0);
    }

}
