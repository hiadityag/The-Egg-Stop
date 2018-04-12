package com.example.aditya.theeggstop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
public class Splash extends Activity
{
    private Boolean firstTime = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        setContentView(R.layout.splash);
        new Timer().schedule(new TimerTask(){
            public void run() {
                Splash.this.runOnUiThread(new Runnable() {
                    public void run() {
                        nextActivity();
                    }
                });
            }
        }, 2000);

    }
    private boolean isFirstTime() {
        if (firstTime == null)
        {
            SharedPreferences mPreferences = this.getSharedPreferences("first_open", Context.MODE_PRIVATE);
            firstTime = mPreferences.getBoolean("firstTime", true);
            //int second=mPreferences.edit();
            if (firstTime) {
                SharedPreferences.Editor editor = mPreferences.edit();
                editor.putBoolean("firstTime", false);
                editor.commit();
            }
        }
        return firstTime;
    }
    public void nextActivity()
    {
        if(isFirstTime())
        {
            Intent i=new Intent(Splash.this,Main2Activity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_down);
            finish();
        }
        else
            {
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("valuestore", MODE_PRIVATE);
            String name1, name2, pass1;
            name1 = sharedPreferences.getString("name1", "");
            pass1 = sharedPreferences.getString("pass1", "");
            if (name1.isEmpty() || pass1.isEmpty())
            {
                Toast.makeText(getApplicationContext(), "Enter Credentials to proceed", Toast.LENGTH_LONG).show();
                Intent i = new Intent(Splash.this, Main2Activity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_down);
                finish();
            }
            else
            {
                Intent i = new Intent(Splash.this, Main2Activity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_down);
                finish();
            }
        }
    }
}

