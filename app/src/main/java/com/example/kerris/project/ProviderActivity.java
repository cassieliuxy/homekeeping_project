package com.example.kerris.project;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class ProviderActivity extends AppCompatActivity {

    TextView tex, hintpro;
    EditText addre;
    EditText phonenum;
    EditText compan;
    EditText descr;
    Spinner spiLicen;

    String lic;
    String uname = MainActivity.u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);

        tex = (TextView) findViewById(R.id.textprovider);
        hintpro = (TextView) findViewById(R.id.hintprovider);
        addre = (EditText) findViewById(R.id.addbox);
        phonenum = (EditText) findViewById(R.id.phonebox);
        compan = (EditText) findViewById(R.id.companybox);
        descr = (EditText) findViewById(R.id.descripbox);

        spiLicen = (Spinner) findViewById(R.id.spinnerLic);

        tex.setText("Welcome " + MainActivity.u + " ! You are logged as Service Provider");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item); //系统sdk里面的R文件
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.add("yes");
        adapter.add("no");
        spiLicen.setAdapter(adapter);
        spiLicen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (view instanceof TextView) {
                    Toast.makeText(ProviderActivity.this, ((TextView) view).getText().toString(), Toast.LENGTH_SHORT).show();
                    lic = ((TextView) view).getText().toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                hintpro.setText("Please choose the licensed.");
            }
        });
    }

    public void newProvider(View view) {
        String address = addre.getText().toString().trim();
        String phonenumber = phonenum.getText().toString().trim();
        String companyname = compan.getText().toString().trim();
        String description = descr.getText().toString().trim();

        if (address.isEmpty()) {
            hintpro.setText("Invalid address.");
        } else if (phonenumber.isEmpty()) {
            hintpro.setText("Invalid phone number.");
        } else if (companyname.isEmpty()) {
            hintpro.setText("Invalid company name.");
        } else{
            // TODO: get from Database
            Providerdb providerdb = new Providerdb(this);
            Provider provider = providerdb.findProvider(uname);
            if (provider != null) {
                providerdb.changeProvider(uname, address, phonenumber,companyname,description,lic);
                hintpro.setText("The service provider already exists. All information is updated.");
            }else {
                // TODO: add to database
                Provider provider1 = new Provider(uname, address, phonenumber,companyname,description,lic);
                providerdb.addProvider(provider1);
                hintpro.setText("Submit successfully.");
            }
        }
    }

    public void OnSetChooseButton(View view) {
        Providerdb providerdb = new Providerdb(this);
        Provider provider = providerdb.findProvider(uname);

        if (provider == null){
            hintpro.setText("Please submit personal information first.");
        }else{
            Intent intent = new Intent(getApplicationContext(), ProviderChooseActivity.class);
            startActivityForResult(intent, 0);
        }
    }

}
