package com.example.kerris.project;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HORateActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    TextView hint;
    EditText comment;
    Spinner spiRate;
    String cs, Rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horate);

        hint = (TextView) findViewById(R.id.hintcs);
        comment = (EditText)findViewById(R.id.hocomment);
        spiRate = (Spinner) findViewById(R.id.rateho);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item); //系统sdk里面的R文件
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.add("1");
        adapter2.add("2");
        adapter2.add("3");
        adapter2.add("4");
        adapter2.add("5");
        spiRate.setAdapter(adapter2);
        spiRate.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        switch (parent.getId()) {
            case R.id.rateho:
                if (view instanceof TextView) {
                    Toast.makeText(HORateActivity.this, ((TextView) view).getText().toString(), Toast.LENGTH_SHORT).show();
                    Rate = ((TextView) view).getText().toString();
                }
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        switch (parent.getId()) {
            case R.id.rateho:
                hint.setText("Please choose the rate.");
                break;
        }
    }

    public void submitbtn(View view){
        String com = comment.getText().toString();

        Pattern p = Pattern.compile("[0-9]*");
        Matcher m = p.matcher(com);

        if( m.matches()){
            hint.setText("Invalid comment.");
        }else{

            HomeownerSelectdb homeownerselectdb = new HomeownerSelectdb(this);
            HomeownerSelect homeownerselect = homeownerselectdb.findHOService(MainActivity.u,HoViewActivity.sn,HoViewActivity.pn,HoViewActivity.day,HoViewActivity.starttime,HoViewActivity.endtime);
            if (homeownerselect.getComment()==null || homeownerselect.getComment().length()<=0){
                hint.setText("Submit successfully.");
            }else{
                hint.setText("You have commented and rated, and new comments and ratings have been updated");
            }
            homeownerselectdb.addratecom(Rate,com,MainActivity.u,HoViewActivity.sn,HoViewActivity.pn,HoViewActivity.day,HoViewActivity.starttime,HoViewActivity.endtime);
        }
    }

}
