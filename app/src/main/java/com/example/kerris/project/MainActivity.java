package com.example.kerris.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView hint;
    EditText usr;
    EditText psw;
    Spinner spinner;
    public static String rol;
    public static String u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hint = (TextView) findViewById(R.id.hinttext);
        usr = (EditText) findViewById(R.id.usernamebox);
        psw = (EditText) findViewById(R.id.passwordbox);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item); //系统sdk里面的R文件
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.add("Administration");
        adapter.add("ServiceProvider");
        adapter.add("HomeOwner");
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (view instanceof TextView) {
                    Toast.makeText(MainActivity.this, ((TextView) view).getText().toString(), Toast.LENGTH_SHORT).show();
                    rol = ((TextView) view).getText().toString();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                hint.setText("Please choose the role.");
            }
        });

    }

    public void newUser(View view) {
        String password = psw.getText().toString().trim();
        u = usr.getText().toString().trim();

        if(u.isEmpty()){
            if(password.isEmpty()){
                hint.setText("Invalid username and password.");
            }else{
                hint.setText("Invalid username.");
            }
        }else{
            if(password.isEmpty()){
                hint.setText("Invalid password.");
            }else{
                // TODO: get from Database
                Userdb userdb = new Userdb(this);
                User user = userdb.findUser(u, rol);
                if (user != null) {
                    hint.setText("The user already exists. Please create again.");
                    usr.setText("");
                    psw.setText("");
                } else {
                    if(rol == "Administration"){
                        User user1 = userdb.findUser3(rol);
                        if(user1 != null){
                            hint.setText("The Administration already exists. Please create user in another role.");
                            usr.setText("");
                            psw.setText("");
                        }else{
                            User user2 = new User(u, password, rol);
                            userdb.addUser(user2);
                            hint.setText("Create account successfully. Please click LOGIN.");
                        }
                    }else{
                        // TODO: add to database
                        User user3 = new User(u, password, rol);
                        userdb.addUser(user3);
                        hint.setText("Create account successfully. Please click LOGIN.");
                    }
                }
            }
        }
    }

    public void OnSetAvatarButton(View view){
        String password = psw.getText().toString().trim();
        u = usr.getText().toString().trim();

        if(u.isEmpty()){
            if(password.isEmpty()){
                hint.setText("Invalid username and password.");
            }else{
                hint.setText("Invalid username.");
            }
        }else {
            if (password.isEmpty()) {
                hint.setText("Invalid password.");
            } else {
                Userdb userdb = new Userdb(this);
                User user = userdb.findUser2(u, password, rol);
                if (user != null) {
                    if(rol == "Administration"){
                        Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                        startActivityForResult(intent, 0);
                    }else if(rol == "ServiceProvider"){
                        Intent intent = new Intent(getApplicationContext(), ProviderActivity.class);
                        startActivityForResult(intent, 0);
                    }
                    else if(rol == "HomeOwner"){
                        Intent intent = new Intent(getApplicationContext(), HomeownerActivity.class);
                        startActivityForResult(intent, 0);
                    }
                    usr.setText("");
                    psw.setText("");
                } else {
                    hint.setText("This account doesn't exist. Please Create.");
                }
            }
        }
    }
}
