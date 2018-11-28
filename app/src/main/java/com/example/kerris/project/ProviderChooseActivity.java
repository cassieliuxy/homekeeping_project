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

public class ProviderChooseActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView hintpc;
    Spinner spiSer, spiDay, spiStart, spiEnd;
    Button addserBtn, viewserBtn, addtimeBtn, viewtimeBtn;

    String ser, day, starttime, endtime, avail;
    String rate;
    String uname = MainActivity.u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_choose);

        hintpc = (TextView) findViewById(R.id.hintpchoose);

        addserBtn = (Button) findViewById(R.id.addservice);
        viewserBtn = (Button) findViewById(R.id.viewservice);
        addtimeBtn = (Button) findViewById(R.id.addtime);
        viewtimeBtn = (Button) findViewById(R.id.viewtime);

        spiSer = (Spinner) findViewById(R.id.spinnerserv);
        spiDay = (Spinner) findViewById(R.id.spinnerday);
        spiStart = (Spinner) findViewById(R.id.spinnerstart);
        spiEnd = (Spinner) findViewById(R.id.spinnerend);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Servicedb servicedb = new Servicedb(this);
        Cursor ser = servicedb.getListServices();
        int num = ser.getCount();
        if (num == 0) {
            hintpc.setText("The Service List is empty.");
        } else {
            int i = 0;
            while (ser.moveToNext()) {
                adapter1.add(ser.getString(0));
                i++;
            }
        }
        spiSer.setAdapter(adapter1);
        spiSer.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item); //系统sdk里面的R文件
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.add("Monday");
        adapter2.add("Tuesday");
        adapter2.add("Wednesday");
        adapter2.add("Thursday");
        adapter2.add("Friday");
        adapter2.add("Saturday");
        adapter2.add("Sunday");
        spiDay.setAdapter(adapter2);
        spiDay.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item); //系统sdk里面的R文件
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        String A[] = new String[30];
        for (int i = 0; i < 24; i++) {
            A[i] = String.valueOf(i);
            adapter3.add(A[i]);
        }
        spiStart.setAdapter(adapter3);
        spiStart.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item); //系统sdk里面的R文件
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        String a[] = new String[30];
        for (int i = 1; i < 25; i++) {
            a[i] = String.valueOf(i);
            adapter4.add(a[i]);
        }
        spiEnd.setAdapter(adapter4);
        spiEnd.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        switch (parent.getId()) {
            case R.id.spinnerserv:
                if (view instanceof TextView) {
                    Toast.makeText(ProviderChooseActivity.this, ((TextView) view).getText().toString(), Toast.LENGTH_SHORT).show();
                    ser = ((TextView) view).getText().toString();
                }
                break;
            case R.id.spinnerday:
                if (view instanceof TextView) {
                    Toast.makeText(ProviderChooseActivity.this, ((TextView) view).getText().toString(), Toast.LENGTH_SHORT).show();
                    day = ((TextView) view).getText().toString();
                }
                break;
            case R.id.spinnerstart:
                if (view instanceof TextView) {
                    Toast.makeText(ProviderChooseActivity.this, ((TextView) view).getText().toString(), Toast.LENGTH_SHORT).show();
                    starttime = ((TextView) view).getText().toString();
                }
                break;
            case R.id.spinnerend:
                if (view instanceof TextView) {
                    Toast.makeText(ProviderChooseActivity.this, ((TextView) view).getText().toString(), Toast.LENGTH_SHORT).show();
                    endtime = ((TextView) view).getText().toString();
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        switch (parent.getId()) {
            case R.id.spinnerserv:
                hintpc.setText("Please choose the service.");
                break;
            case R.id.spinnerday:
                hintpc.setText("Please choose the day.");
                break;
            case R.id.spinnerstart:
                hintpc.setText("Please choose the start time.");
                break;
            case R.id.spinnerend:
                hintpc.setText("Please choose the end time.");
                break;
        }
    }

    public void newProviderService(View view) {
        ProviderServicedb providerservicedb = new ProviderServicedb(this);
        ProviderService providerservice = providerservicedb.findProviderService(uname,ser);
        if (providerservice != null) {
            hintpc.setText("The service has already chosen. Please choose another service.");
        } else {
            Servicedb servicedb = new Servicedb(this);
            Service service = servicedb.findService(ser);
            rate = service.getRate();
            ProviderService providerservice1 = new ProviderService(uname, ser,rate);
            providerservicedb.addProviderService(providerservice1);
            hintpc.setText("Choose service successfully.");
        }
    }

    public void viewProvideService (View view) {
        Intent intent = new Intent(getApplicationContext(), SPServiceActivity.class);
        startActivityForResult(intent, 0);
    }

    public void newProviderTime(View view) {
        int start = Integer.parseInt(starttime);
        int end = Integer.parseInt(endtime);
        avail = "available";

        if(start > end){
            hintpc.setText("Error. The start time cannot exceed the end time. Please choose again.");
        }else if(start == end){
            hintpc.setText("Error. The start time cannot be equal to the end time.Please choose again.");
        }else if(start == 0 && end == 24){
            hintpc.setText("Invalid time.Please choose again.");
        }else{
            ProviderTimedb providertimedb = new ProviderTimedb(this);
            ProviderTime providertime = providertimedb.findProviderTime(uname,day,starttime,endtime,avail);
            if (providertime != null) {
                hintpc.setText("The time has been already chosen. Please choose another time.");
            } else {
                ProviderTime providertime1 = new ProviderTime(uname,day,starttime,endtime,avail);
                providertimedb.addProviderTime(providertime1);
                hintpc.setText("Choose time successfully.");
            }
        }
    }

    public void viewProvideTime (View view) {
        Intent intent = new Intent(getApplicationContext(), SPTimeActivity.class);
        startActivityForResult(intent, 0);
    }

}