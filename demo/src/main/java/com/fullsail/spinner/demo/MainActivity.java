package com.fullsail.spinner.demo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class MainActivity extends Activity {

    final String TAG = "Spinner Demo";
    Spinner mOSSpinner;
    ArrayAdapter<String> mOSAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOSSpinner = (Spinner) findViewById(R.id.osspinner);
        ArrayList<String> osList = new ArrayList(Arrays.asList(getResources().getStringArray(R.array.mobileos)));
        mOSAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,osList);

        mOSSpinner.setAdapter(mOSAdapter);

        mOSSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "Item Selected");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i(TAG, "Nothing Selected");
            }
        });

        //Button Methods
        Button insertButton = (Button) findViewById(R.id.insertitembutton);
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOSAdapter.insert("Firefox OS",2);
            }
        });

        Button toggleButton = (Button) findViewById(R.id.toggle);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOSAdapter.getPosition("Windows Phone") == -1) {
                    mOSAdapter.add("Windows Phone");
                } else {
                    mOSAdapter.remove("Windows Phone");
                }
            }
        });

        Button addButton = (Button) findViewById(R.id.addothers);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] others = {"Ubuntu", "Tizen"};
                for(String other: others){
                    if(mOSAdapter.getPosition(other) == -1) mOSAdapter.add(other);
                }
            }
        });

        Button sortButton = (Button) findViewById(R.id.sortlist);
        sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOSAdapter.sort(new Comparator<String>() {
                    public int compare(String a, String b) {
                        return a.compareTo(b);
                    }
                });
            }
        });

        Button removeButton = (Button) findViewById(R.id.removeall);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOSAdapter.clear();
            }
        });

    }

}
