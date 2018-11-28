package com.example.kerris.project;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ServiceActivity extends AppCompatActivity {

    Servicedb sdb;
    ArrayList<Service> servicelist;
    ListView lvServices;
    Service s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        sdb = new Servicedb(this);
        servicelist = new ArrayList<>();
        Cursor data = sdb.getListContents();
        int num = data.getCount();
        if(num ==0){
            Toast.makeText(ServiceActivity.this, "The Database is empty.",Toast.LENGTH_LONG).show();
        }else{
            int i =0;
            while(data.moveToNext()){
                s = new Service(data.getString(1),data.getString(2));
                servicelist.add(i,s);
                System.out.println(data.getString(1)+" "+data.getString(2));
                System.out.println(servicelist.get(i).getServiceName());
                i++;
            }
            ServiceList adapter = new ServiceList(this, R.layout.layput_service_list, servicelist);
            lvServices = (ListView) findViewById(R.id.listservice);
            lvServices.setAdapter(adapter);
        }
    }
}
