package com.major.touristguide;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

public class Itinerary extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<String> itineraryIds;
    private String days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Itinerary");

        Bundle extras = getIntent().getExtras();
        itineraryIds = (ArrayList<String>)getIntent().getSerializableExtra("itineraryIds");
        System.out.println("array"+itineraryIds);
        days = extras.getString("days");
        System.out.println("itinerary" +extras.getString("days"));

         int day;
        if (!TextUtils.isEmpty(days) && TextUtils.isDigitsOnly(days)) {
            day = Integer.parseInt(days);
        } else {
            day = 0;
        }
        String title[] = new String[day];
        for(int i=1;i<=day;i++)
            title[i-1]="Day"+i;

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),title,itineraryIds);
        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(Itinerary.this, MainHome.class));
        finish();

    }

}
