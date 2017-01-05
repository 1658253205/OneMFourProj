package com.example.administrator.onemfourproj.Activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.administrator.onemfourproj.R;

public class Secend_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_secend);
    }
}
