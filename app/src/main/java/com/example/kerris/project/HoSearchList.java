package com.example.kerris.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HoSearchList extends ArrayAdapter<HomeownerSearch> {

    private LayoutInflater mInflater;
    private ArrayList<HomeownerSearch> homeownersearchs;
    private int mViewResourceId;

    public HoSearchList(Context context, int textViewResourceId, ArrayList<HomeownerSearch> homeownersearchs) {
        super(context, R.layout.layout_hosearch_list, homeownersearchs);
        this.homeownersearchs = homeownersearchs;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    @Override
    public int getCount() {
        return homeownersearchs.size();
    }

    @Override
    public HomeownerSearch getItem(int position) {
        return homeownersearchs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);
        HomeownerSearch homeownersearch = homeownersearchs.get(position);

        if(homeownersearch != null){
            TextView textViewName = (TextView) convertView.findViewById(R.id.SN);
            TextView textViewName1 = (TextView) convertView.findViewById(R.id.PN);
            TextView textViewName2 = (TextView) convertView.findViewById(R.id.RATE);
            TextView textViewName3 = (TextView) convertView.findViewById(R.id.DAY);
            TextView textViewName4 = (TextView) convertView.findViewById(R.id.FROM);
            TextView textViewName5 = (TextView) convertView.findViewById(R.id.TO);
            TextView textViewName6 = (TextView) convertView.findViewById(R.id.AVALIABLE);

            if(textViewName != null){
                textViewName.setText(homeownersearch.getServiceName());
                textViewName1.setText(homeownersearch.getProviderName());
                textViewName2.setText(homeownersearch.getRate());
                textViewName3.setText(homeownersearch.getDAY());
                textViewName4.setText(homeownersearch.getStartTime());
                textViewName5.setText(homeownersearch.getEndTime());
                textViewName6.setText(homeownersearch.getAvailable());
            }
        }

        return convertView;
    }

}
