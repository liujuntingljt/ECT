package com.hbsd.rjxy.ect.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hbsd.rjxy.ect.R;

public class ShowServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_service);
    }
    public void onClicked(View v){
        switch (v.getId()){
            case R.id.iv_rtn:
                finish();
                break;
        }
    }
}
