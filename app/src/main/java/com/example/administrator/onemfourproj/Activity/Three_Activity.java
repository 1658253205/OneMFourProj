package com.example.administrator.onemfourproj.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.onemfourproj.R;

public class Three_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_three);
    }
}
