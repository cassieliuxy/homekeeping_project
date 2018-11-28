package com.example.kerris.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HoViewList extends ArrayAdapter<HomeownerSelect> {
    private LayoutInflater mInflater;
    private ArrayList<HomeownerSelect> homeownerselects;
    private int mViewResourceId;

    public HoViewList(Context context, int textViewResourceId, ArrayList<HomeownerSelect> homeownerselects) {
        super(context, R.layout.layout_hoview_list, homeownerselects);
        this.homeownerselects = homeownerselects;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    @Override
    public int getCount() {
        return homeownerselects.size();
    }

    @Override
    public HomeownerSelect getItem(int position) {
        return homeownerselects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);
        HomeownerSelect homeownerselect = homeownerselects.get(position);

        if(homeownerselect != null){
            TextView textViewName = (TextView) convertView.findViewById(R.id.viewSN);
            TextView textViewName1 = (TextView) convertView.findViewById(R.id.viewPN);
            TextView textViewName2 = (TextView) convertView.findViewById(R.id.viewRATE);
            TextView textViewName3 = (TextView) convertView.findViewById(R.id.viewDAY);
            TextView textViewName4 = (TextView) convertView.findViewById(R.id.viewFROM);
            TextView textViewName5 = (TextView) convertView.findViewById(R.id.viewTO);

            if(textViewName != null){
                textViewName.setText(homeownerselect.getServiceName());
                textViewName1.setText(homeownerselect.getProviderName());
                textViewName2.setText(homeownerselect.getRate());
                textViewName3.setText(homeownerselect.getDAY());
                textViewName4.setText(homeownerselect.getStartTime());
                textViewName5.setText(homeownerselect.getEndTime());
            }
        }

        return convertView;
    }
}
