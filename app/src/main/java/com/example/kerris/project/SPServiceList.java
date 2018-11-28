package com.example.kerris.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SPServiceList extends ArrayAdapter<ProviderService> {
    private LayoutInflater mInflater;
    private ArrayList<ProviderService> providerservices;
    private int mViewResourceId;

    public SPServiceList(Context context, int textViewResourceId, ArrayList<ProviderService> providerservices) {
        super(context, R.layout.layout_spservice_list, providerservices);
        this.providerservices = providerservices;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    @Override
    public int getCount() {
        return providerservices.size();
    }

    @Override
    public ProviderService getItem(int position) {
        return providerservices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);
        ProviderService providerservice = providerservices.get(position);

        if(providerservice != null){
            TextView textViewName = (TextView) convertView.findViewById(R.id.spservicename);
            TextView textViewName1 = (TextView) convertView.findViewById(R.id.spservicerate);

            if(textViewName != null){
                textViewName.setText(providerservice.getServiceName());
                textViewName1.setText(providerservice.getRate());
            }
        }

        return convertView;
    }
}
