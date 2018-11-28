package com.example.kerris.project;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SPServiceActivity extends AppCompatActivity {

    TextView hint;
    ProviderServicedb psdb;
    ArrayList<ProviderService> pslist;
    ListView lvspservice;
    ProviderService ps;
    String ser,u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spservice);

        hint = (TextView)findViewById(R.id.hintspservice);

        item();

        SPServiceList adapter = new SPServiceList(this, R.layout.layout_spservice_list, pslist);
        lvspservice = (ListView) findViewById(R.id.listspservice);
        lvspservice.setAdapter(adapter);

        //set selected item
        lvspservice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //delete
                u = pslist.get(position).getUserName();
                ser = pslist.get(position).getServiceName();
                removeProviderService();
            }
        });
    }

    public void item() {
        psdb = new ProviderServicedb(this);
        pslist = new ArrayList<>();
        Cursor data = psdb.getListContents(MainActivity.u);
        int num = data.getCount();
        if (num == 0) {
            Toast.makeText(SPServiceActivity.this, "The Database is empty.", Toast.LENGTH_LONG).show();
        } else {
            int i = 0;
            while (data.moveToNext()) {
                ps = new ProviderService(data.getString(1), data.getString(2),data.getString(3));
                pslist.add(i, ps);
                System.out.println(data.getString(2)+" "+data.getString(3));
                System.out.println(pslist.get(i).getServiceName());
                i++;
            }
        }
    }


    public void removeProviderService () {
        ProviderServicedb providerservicedb = new ProviderServicedb(this);
        boolean result = providerservicedb.deleteProviderService(u, ser);
        if (result) {
            hint.setText("Delete service successfully.");
        } else {
            hint.setText("Delete service unsuccessfully.");
        }

        pslist.clear();
        item();
        SPServiceList adapter2 = new SPServiceList(this, R.layout.layout_spservice_list, pslist);
        ListView lvsptime2 = (ListView) findViewById(R.id.listspservice);
        lvsptime2.setAdapter(adapter2);
    }

    public void removeall(View view){
        if(pslist.isEmpty()){
            hint.setText("The Database is empty.");
        }else{
            ProviderServicedb providerservicedb = new ProviderServicedb(this);
            boolean result1 = providerservicedb.clear();
            if (result1) {
                hint.setText("Clear all services successfully.");
            } else {
                hint.setText("Clear all services unsuccessfully.");
            }

            pslist.clear();
            SPServiceList adapter1 = new SPServiceList(this, R.layout.layout_spservice_list, pslist);
            ListView lvsptime1 = (ListView) findViewById(R.id.listspservice);
            lvsptime1.setAdapter(adapter1);
        }
    }
}
