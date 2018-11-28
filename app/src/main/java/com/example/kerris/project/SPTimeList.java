package com.example.kerris.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SPTimeList extends ArrayAdapter<ProviderTime> {

    private LayoutInflater mInflater;
    private ArrayList<ProviderTime> providertimes;
    private int mViewResourceId;

    public SPTimeList(Context context, int textViewResourceId, ArrayList<ProviderTime> providertimes) {
        super(context, R.layout.layout_sptime_list, providertimes);
        this.providertimes = providertimes;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    @Override
    public int getCount() {
        return providertimes.size();
    }

    @Override
    public ProviderTime getItem(int position) {
        return providertimes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);
        ProviderTime providertime = providertimes.get(position);

        if(providertime != null){
            TextView textViewName = (TextView) convertView.findViewById(R.id.spday);
            TextView textViewName1 = (TextView) convertView.findViewById(R.id.spstarttime);
            TextView textViewName2 = (TextView) convertView.findViewById(R.id.spendtime);
            TextView textViewName3 = (TextView) convertView.findViewById(R.id.spavailable);

            if(textViewName != null){
                textViewName.setText(providertime.getDAY());
                textViewName1.setText(providertime.getStartTime());
                textViewName2.setText(providertime.getEndTime());
                textViewName3.setText(providertime.getAvailable());
            }
        }

        return convertView;
    }

}
