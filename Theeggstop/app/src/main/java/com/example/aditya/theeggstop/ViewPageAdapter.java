package com.example.aditya.theeggstop;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Aditya on 5/21/2017.
 */

public class ViewPageAdapter extends PagerAdapter
{

    private Context context;
    private LayoutInflater layoutInflater;
    private Integer[] images = {R.drawable.banner1, R.drawable.bannner2, R.drawable.banner3};

    public  ViewPageAdapter(Context context)
    {
        this.context=context;
    }
    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
    public Object instantiateItem(ViewGroup container, final int position)
    {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.customlayout, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setImageResource(images[position]);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(position==0)
                {
                    Toast.makeText(context, "Hey Its Slide 1", Toast.LENGTH_SHORT).show();
                }
                if(position==1)
                {
                    Toast.makeText(context, "Hey Its Slide 2", Toast.LENGTH_SHORT).show();
                }
                if(position==2)
                {
                    Toast.makeText(context, "Hey Its Slide 3", Toast.LENGTH_SHORT).show();
                }

            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(position==0)
                {
                    Toast.makeText(context, "Hey Its long Slide 1", Toast.LENGTH_SHORT).show();
                }
                if(position==1)
                {
                    Toast.makeText(context, "Hey Its long Slide 2", Toast.LENGTH_SHORT).show();
                }
                if(position==2)
                {
                    Toast.makeText(context, "Hey Its long Slide 3", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });
        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);

        return view;

    }


    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }





}
