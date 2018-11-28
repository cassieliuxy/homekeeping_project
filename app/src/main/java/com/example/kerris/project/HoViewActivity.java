package com.example.kerris.project;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HoViewActivity extends AppCompatActivity {

    TextView hint;
    HomeownerSelectdb hosedb;
    ArrayList<HomeownerSelect> hoselist;
    ListView lvhoview;
    HomeownerSelect hose;
    public static String sn, pn, ra, day, starttime, endtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ho_view);
        hint = (TextView)findViewById(R.id.hinthoview);

        item();

        HoViewList adapter = new HoViewList(this, R.layout.layout_hoview_list, hoselist);
        lvhoview = (ListView) findViewById(R.id.hoviewlist);
        lvhoview.setAdapter(adapter);

        //set selected item
        lvhoview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //delete
                sn = hoselist.get(position).getServiceName();
                pn = hoselist.get(position).getProviderName();
                ra = hoselist.get(position).getRate();
                day = hoselist.get(position).getDAY();
                starttime = hoselist.get(position).getStartTime();
                endtime = hoselist.get(position).getEndTime();
                //removeHOService();
                Intent intent = new Intent(getApplicationContext(), HORateActivity.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    public void item() {
        hosedb = new HomeownerSelectdb(this);
        hoselist = new ArrayList<>();
        Cursor data = hosedb.getListContents();
        int num = data.getCount();
        if (num == 0) {
            Toast.makeText(HoViewActivity.this, "The Database is empty.", Toast.LENGTH_LONG).show();

        } else {
            int i = 0;
            while (data.moveToNext()) {
                hose = new HomeownerSelect(data.getString(1), data.getString(2),data.getString(3),data.getString(4),data.getString(5),data.getString(6),data.getString(7));
                hoselist.add(i, hose);
                System.out.println(data.getString(2)+" "+data.getString(3)+" "+data.getString(4)+" "+data.getString(5)+" "+data.getString(6)+" "+data.getString(7));
                System.out.println(hoselist.get(i).getServiceName());
                i++;
            }
        }
    }


    public void removeHOService () {
        HomeownerSelectdb homeownerselectdb = new HomeownerSelectdb(this);
        boolean result = homeownerselectdb.deleteHOService(MainActivity.u,sn,pn,ra,day,starttime,endtime);
        if (result) {
            hint.setText("Delete service successfully.");

            ProviderTimedb providerTimedb = new ProviderTimedb(this);
            providerTimedb.changeavail2(pn, day, starttime, endtime);
            HomeownerSearchdb homeownersearchdb = new HomeownerSearchdb(this);
            homeownersearchdb.changeAvail1(pn, day, starttime, endtime);

        } else {
            hint.setText("Delete service unsuccessfully.");
        }

        hoselist.clear();
        item();
        HoViewList adapter2 = new HoViewList(this, R.layout.layout_hoview_list, hoselist);
        ListView lvsptime2 = (ListView) findViewById(R.id.hoviewlist);
        lvsptime2.setAdapter(adapter2);
    }
}
