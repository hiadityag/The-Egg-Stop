package com.example.aditya.theeggstop;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Aditya on 5/21/2017.
 */

public class hometab extends Fragment
{
    Button b1,b2,b3;
    ViewPager viewpage;
    LinearLayout dotsslider;
    private int dotscount;
    private ImageView[] dots;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.hometab,container,false);
        dotsslider=(LinearLayout) rootView.findViewById(R.id.dota);
        b1=(Button) rootView.findViewById(R.id.menu);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
        b2=(Button) rootView.findViewById(R.id.media);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent in = new Intent(getActivity(),Media.class);
                startActivity(in);
            }
        });
        b3=(Button) rootView.findViewById(R.id.frenchise);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        viewpage=(ViewPager) rootView.findViewById(R.id.viewpager);

        ViewPageAdapter viewPageAdapter= new ViewPageAdapter(getContext());

        viewpage.setAdapter(viewPageAdapter);

        dotscount= viewPageAdapter.getCount();
        dots=new ImageView[dotscount];


        for(int i=0;i <dotscount;i++)
        {
            dots[i]= new ImageView(getContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(),R.drawable.nonactive_dots));

            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8,0,8,0);
            dotsslider.addView(dots[i],params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(),R.drawable.active_dots));

        viewpage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position)
            {

                for(int i=0;i<dotscount;i++)
                {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(), R.drawable.nonactive_dots));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getActivity().getApplicationContext(),R.drawable.active_dots));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(),2000,4000);
        return rootView;



    }

    class MyTimerTask extends TimerTask
    {

        @Override
        public void run() {

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(viewpage.getCurrentItem()==0)
                    {
                        viewpage.setCurrentItem(1);
                    }
                    else  if(viewpage.getCurrentItem()==1)
                    {
                        viewpage.setCurrentItem(2);
                    }
                    else
                    {
                        viewpage.setCurrentItem(0);
                    }
                }
            });
        }
    }
}
