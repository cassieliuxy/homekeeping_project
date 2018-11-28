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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HomeownerActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{

    TextView tex, hintho;
    EditText servicename;
    EditText rateperhour;
    Spinner hoday, hostartt,hoendt;

    String uname = MainActivity.u;
    String ser, rate, day, starttime, endtime;

    public static String SS,RR,DD,ST,ET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeowner);

        tex = (TextView) findViewById(R.id.texthomeowner);
        hintho = (TextView) findViewById(R.id.hinthomeowner);
        servicename = (EditText) findViewById(R.id.hoservicenamebox);
        rateperhour = (EditText) findViewById(R.id.horatingbox);;

        hoday = (Spinner) findViewById(R.id.hoday);
        hostartt = (Spinner) findViewById(R.id.hostarttime);
        hoendt = (Spinner) findViewById(R.id.hoendtime);

        tex.setText("Welcome " + MainActivity.u + " ! You are logged as Home Owner");

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item); //系统sdk里面的R文件
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.add("Monday");
        adapter2.add("Tuesday");
        adapter2.add("Wednesday");
        adapter2.add("Thursday");
        adapter2.add("Friday");
        adapter2.add("Saturday");
        adapter2.add("Sunday");
        hoday.setAdapter(adapter2);
        hoday.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item); //系统sdk里面的R文件
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        String A[] = new String[30];
        for (int i = 0; i < 24; i++) {
            A[i] = String.valueOf(i);
            adapter3.add(A[i]);
        }
        hostartt.setAdapter(adapter3);
        hostartt.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item); //系统sdk里面的R文件
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        String a[] = new String[30];
        for (int i = 1; i < 25; i++) {
            a[i] = String.valueOf(i);
            adapter4.add(a[i]);
        }
        hoendt.setAdapter(adapter4);
        hoendt.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        switch (parent.getId()) {
            case R.id.hoday:
                if (view instanceof TextView) {
                    Toast.makeText(HomeownerActivity.this, ((TextView) view).getText().toString(), Toast.LENGTH_SHORT).show();
                    day = ((TextView) view).getText().toString();
                }
                break;
            case R.id.hostarttime:
                if (view instanceof TextView) {
                    Toast.makeText(HomeownerActivity.this, ((TextView) view).getText().toString(), Toast.LENGTH_SHORT).show();
                    starttime = ((TextView) view).getText().toString();
                }
                break;
            case R.id.hoendtime:
                if (view instanceof TextView) {
                    Toast.makeText(HomeownerActivity.this, ((TextView) view).getText().toString(), Toast.LENGTH_SHORT).show();
                    endtime = ((TextView) view).getText().toString();
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        switch (parent.getId()) {
            case R.id.hoday:
                hintho.setText("Please choose the day.");
                break;
            case R.id.hostarttime:
                hintho.setText("Please choose the start time.");
                break;
            case R.id.hoendtime:
                hintho.setText("Please choose the end time.");
                break;
        }
    }

    public void searchservice(View view){
        ser = servicename.getText().toString().trim();
        rate = rateperhour.getText().toString().trim();

        DD = day;
        ST = starttime;
        ET = endtime;

        int start = Integer.parseInt(starttime);
        int end = Integer.parseInt(endtime);
        if(start > end){
            hintho.setText("Error. The start time cannot exceed the end time. Please choose again.");
        }else if(start == end){
            hintho.setText("Error. The start time cannot be equal to the end time.Please choose again.");
        }else if(start == 0 && end == 24){
            hintho.setText("Invalid time.Please choose again.");
        }else{
            ProviderTimedb providertimedb = new ProviderTimedb(this);
            Cursor data = providertimedb.getListContents2(day,starttime,endtime);
            HomeownerSearchdb homeownersearchdb = new HomeownerSearchdb(this);

            if(data.getCount()>0){
                int i =0;
                while(data.moveToNext()){
                    ProviderServicedb providerservicedb = new ProviderServicedb(this);
                    Cursor data1 = providerservicedb.getListContents(data.getString(1));
                    if(data1.getCount()>0){
                        int j =0;
                        while(data1.moveToNext()){
                            if(homeownersearchdb.findService(uname,data1.getString(2),data.getString(1),data1.getString(3),day,starttime,endtime)==null){
                                HomeownerSearch homeownersearch = new HomeownerSearch(uname,data1.getString(2),data.getString(1),data1.getString(3),day,starttime,endtime,data.getString(5));
                                homeownersearchdb.addHoServiceSearch(homeownersearch);
                            }
                            j++;
                        }
                    }
                    data1.close();
                    i++;
                }
                data.close();
            }

            if(ser.isEmpty() && rate.isEmpty()){
                SS = "0";
                RR = "0";
                Intent intent = new Intent(getApplicationContext(), HoSearchActivity.class);
                startActivityForResult(intent, 0);
            }else if(ser.length() > 0 && rate.isEmpty()){
                Pattern p = Pattern.compile("[0-9]*");
                Matcher m = p.matcher(ser);
                if(m.matches()){
                    hintho.setText("Invalid service name.");
                }else{
                      SS = ser;
                      RR = "0";
                    Intent intent = new Intent(getApplicationContext(), HoSearchActivity.class);
                    startActivityForResult(intent, 0);
                }
            } else if(ser.isEmpty() && rate.length() > 0){
                SS = "0";
                RR = rate;
                Intent intent = new Intent(getApplicationContext(), HoSearchActivity.class);
                startActivityForResult(intent, 0);
            } else if (ser.length() > 0 && rate.length() > 0){
                Pattern p = Pattern.compile("[0-9]*");
                Matcher m = p.matcher(ser);
                if(m.matches()){
                    hintho.setText("Invalid service name.");
                }else{
                    SS = ser;
                    RR = rate;
                    Intent intent = new Intent(getApplicationContext(), HoSearchActivity.class);
                    startActivityForResult(intent, 0);
                }
            }
        }
    }

    public void viewservices(View view){
        Intent intent = new Intent(getApplicationContext(), HoViewActivity.class);
        startActivityForResult(intent, 0);
    }

}
