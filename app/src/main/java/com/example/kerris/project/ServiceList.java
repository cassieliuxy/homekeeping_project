package com.example.kerris.project;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.content.Context;

import java.util.ArrayList;

public class ServiceList extends ArrayAdapter<Service>{
    //private Activity context;
    private LayoutInflater mInflater;
    private ArrayList<Service> services;
    private int mViewResourceId;

    public ServiceList(Context context, int textViewResourceId, ArrayList<Service> services) {
        super(context, R.layout.layput_service_list, services);
        this.services = services;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    //@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);
        Service service = services.get(position);

        if(service != null){
            TextView textViewName = (TextView) convertView.findViewById(R.id.textViewservicename);
            TextView textViewRate = (TextView) convertView.findViewById(R.id.textViewrate);

            if(textViewName != null){
                textViewName.setText(service.getServiceName());
            }
            if(textViewRate != null){
                textViewRate.setText(service.getRate());
            }
        }

        return convertView;
    }
}
