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
import java.util.Calendar;

public class HoSearchActivity extends AppCompatActivity {

    TextView hint;

    HomeownerSearchdb hosdb;
    ArrayList<HomeownerSearch> hoslist;
    ListView lvhosearch;
    HomeownerSearch hos;
    String sn, pn, ra, day, starttime, endtime, avai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ho_search);

        hint = (TextView) findViewById(R.id.hinthosearch);

        item();

        HoSearchList adapter = new HoSearchList(this, R.layout.layout_hosearch_list, hoslist);
        lvhosearch = (ListView) findViewById(R.id.hosearchlist);
        lvhosearch.setAdapter(adapter);

        //set selected item
        lvhosearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //choose
                sn = hoslist.get(position).getServiceName();
                pn = hoslist.get(position).getProviderName();
                ra = hoslist.get(position).getRate();
                day = hoslist.get(position).getDAY();
                starttime = hoslist.get(position).getStartTime();
                endtime = hoslist.get(position).getEndTime();
                avai = hoslist.get(position).getAvailable();
                HOselectService();
            }
        });
    }

    public void item() {
        hosdb = new HomeownerSearchdb(this);
        hoslist = new ArrayList<>();

        Cursor data = hosdb.getListContents1(HomeownerActivity.DD,HomeownerActivity.ST,HomeownerActivity.ET);
        if(HomeownerActivity.SS == "0" && HomeownerActivity.RR == "0"){
            data = hosdb.getListContents1(HomeownerActivity.DD,HomeownerActivity.ST,HomeownerActivity.ET);
        }else if(HomeownerActivity.SS.length()>0 && HomeownerActivity.RR == "0"){
            data = hosdb.getListContents2(HomeownerActivity.SS,HomeownerActivity.DD,HomeownerActivity.ST,HomeownerActivity.ET);
        }else if(HomeownerActivity.SS == "0" && HomeownerActivity.RR.length()>0){
            data = hosdb.getListContents3(HomeownerActivity.RR,HomeownerActivity.DD,HomeownerActivity.ST,HomeownerActivity.ET);
        }else if(HomeownerActivity.SS.length()>0 && HomeownerActivity.RR.length()>0){
            data = hosdb.getListContents4(HomeownerActivity.SS,HomeownerActivity.RR,HomeownerActivity.DD,HomeownerActivity.ST,HomeownerActivity.ET);
        }

        int num = data.getCount();
        if (num == 0) {
            Toast.makeText(HoSearchActivity.this, "The Database is empty.", Toast.LENGTH_LONG).show();
        } else {
            int i = 0;
            while (data.moveToNext()) {
                hos = new HomeownerSearch(data.getString(1), data.getString(2),data.getString(3),data.getString(4),data.getString(5),data.getString(6),data.getString(7),data.getString(8));
                hoslist.add(i, hos);
                System.out.println(data.getString(2)+" "+data.getString(3)+" "+data.getString(4)+" "+data.getString(5)+" "+data.getString(6)+" "+data.getString(7)+" "+data.getString(8));
                System.out.println(hoslist.get(i).getServiceName());
                i++;
            }
        }
    }

    public void HOselectService() {
        Calendar calendar = Calendar.getInstance();
        boolean isFirstSunday = (calendar.getFirstDayOfWeek() == Calendar.SUNDAY);
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        if(isFirstSunday){
            weekDay = weekDay - 1;
            if(weekDay == 0){
                weekDay = 7;
            }
        }
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int s = Integer.parseInt(starttime);
        HomeownerSelectdb homeownerselectdb = new HomeownerSelectdb(this);
        int i=0;

        if(avai.matches("unavailable")) {
            hint.setText("Invalid time.Please reselect.");
        }
        else if(avai.matches("available")){
            if(weekDay ==1) {
                if (day.matches("Monday")  && s <= hour) {
                    hint.setText("This time is the past time.Please reselect.");
                }else{
                    i = 1;
                }
            }else if(weekDay == 2) {
                if(day.matches("Tuesday") && s > hour){
                    i=1;
                }else if (day.matches("Wednesday") || day.matches("Thursday") || day.matches("Friday") || day.matches("Saturday") || day.matches("Sunday")) {
                    i = 1;
                } else {
                    hint.setText("This time is the past time.Please reselect.");
                }
            }else if(weekDay == 3) {
                if(day.matches("Wednesday") && s > hour){
                    i=1;
                }else if (day.matches("Thursday") || day.matches("Friday") || day.matches("Saturday") || day.matches("Sunday") ) {
                    i = 1;
                } else {
                    hint.setText("This time is the past time.Please reselect.");
                }
            }else if(weekDay == 4) {
                if(day.matches("Thursday") && s > hour){
                    i=1;
                }else if (day.matches("Friday") || day.matches("Saturday") || day.matches("Sunday") ) {
                    i = 1;
                } else {
                    hint.setText("This time is the past time.Please reselect.");
                }
            }else if(weekDay == 5) {
                if(day.matches("Friday") && s > hour){
                    i=1;
                }else if (day.matches("Saturday") || day.matches("Sunday") ) {
                    i = 1;
                } else {
                    hint.setText("This time is the past time.Please reselect.");
                }
            }else if(weekDay == 6) {
                if(day.matches("Saturday") && s > hour){
                    i=1;
                }else if (day.matches("Sunday")) {
                    i = 1;
                } else {
                    hint.setText("This time is the past time.Please reselect.");
                }
            }else if(weekDay == 7) {
                if(day.matches("Sunday") && s > hour){
                    i=1;
                } else {
                    hint.setText("This time is the past time.Please reselect.");
                }
            }else{
                hint.setText("This time is fault.");
            }

            if(i==1){
                HomeownerSelect homeownerselect = new HomeownerSelect(MainActivity.u, sn, pn, ra, day, starttime, endtime);
                homeownerselectdb.addHoServiceSearch(homeownerselect);
                hint.setText("Book service successfully.");

                hosdb.changeavail(pn, day, starttime, endtime);
                ProviderTimedb providerTimedb = new ProviderTimedb(this);
                providerTimedb.changeavail(pn, day, starttime, endtime);

                hoslist.clear();
                item();
                HoSearchList adapter2 = new HoSearchList(this, R.layout.layout_hosearch_list, hoslist);
                ListView lvhosearch2 = (ListView) findViewById(R.id.hosearchlist);
                lvhosearch2.setAdapter(adapter2);
            }
        }

    }
}
