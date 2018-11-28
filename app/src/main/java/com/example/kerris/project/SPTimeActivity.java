package com.example.kerris.project;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class SPTimeActivity extends AppCompatActivity {

    TextView hint;
    ProviderTimedb ptdb;
    ArrayList<ProviderTime> ptlist;
    ListView lvsptime;
    ProviderTime pt;
    String u,d, s, e, a;
    ArrayAdapter<ArrayList> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sptime);

        hint = (TextView)findViewById(R.id.hintsptime);
        item();

        SPTimeList adapter = new SPTimeList(this, R.layout.layout_sptime_list, ptlist);
        lvsptime = (ListView) findViewById(R.id.listsptime);
        lvsptime.setAdapter(adapter);

        //set selected item
        lvsptime.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //delete
                u = ptlist.get(position).getUserName();
                d = ptlist.get(position).getDAY();
                s = ptlist.get(position).getStartTime();
                e = ptlist.get(position).getEndTime();
                a = ptlist.get(position).getAvailable();
                removeProviderTime();
            }
        });
    }

    public void item() {
        ptdb = new ProviderTimedb(this);
        ptlist = new ArrayList<>();
        Cursor data = ptdb.getListContents(MainActivity.u);
        int num = data.getCount();
        if (num == 0) {
            Toast.makeText(SPTimeActivity.this, "The Database is empty.", Toast.LENGTH_LONG).show();
        } else {
            int i = 0;
            while (data.moveToNext()) {
                pt = new ProviderTime(data.getString(1), data.getString(2), data.getString(3), data.getString(4),data.getString(5));
                ptlist.add(i, pt);
                System.out.println(data.getString(2) + " " + data.getString(3) + " " + data.getString(4)+ " " + data.getString(5));
                System.out.println(ptlist.get(i).getDAY());
                i++;
            }
        }

    }

    public void removeProviderTime () {
        ProviderTimedb providertimedb = new ProviderTimedb(this);
        boolean result = providertimedb.deleteProviderTime(u, d,s,e);
        if (result) {
            hint.setText("Delete time successfully.");
        } else {
            hint.setText("Delete time unsuccessfully.");
        }

        ptlist.clear();
        item();
        SPTimeList adapter2 = new SPTimeList(this, R.layout.layout_sptime_list, ptlist);
        ListView lvsptime2 = (ListView) findViewById(R.id.listsptime);
        lvsptime2.setAdapter(adapter2);
    }

    public void removeall(View view){
        if(ptlist.isEmpty()){
            hint.setText("The Database is empty.");
        }else{
            ProviderTimedb providertimedb = new ProviderTimedb(this);
            boolean result1 = providertimedb.clear();
            if (result1) {
                hint.setText("Clear all time successfully.");
            } else {
                hint.setText("Clear all time unsuccessfully.");
            }

            ptlist.clear();
            SPTimeList adapter1 = new SPTimeList(this, R.layout.layout_sptime_list, ptlist);
            ListView lvsptime1 = (ListView) findViewById(R.id.listsptime);
            lvsptime1.setAdapter(adapter1);
        }
    }

}
