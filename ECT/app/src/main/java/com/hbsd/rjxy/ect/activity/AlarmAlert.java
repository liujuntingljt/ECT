package com.hbsd.rjxy.ect.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.hbsd.rjxy.ect.R;

public class AlarmAlert extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        new AlertDialog.Builder(AlarmAlert.this)
                .setIcon(R.drawable.clock)
                .setTitle("ECT闹钟响了!!")
                .setMessage("学习辛苦了!!!")
                .setPositiveButton("关掉它",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int whichButton)
                            {
                                AlarmAlert.this.finish();
                            }
                        })
                .show();
    }
}