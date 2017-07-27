package com.example.client1.mpcharttwo;


import android.app.DatePickerDialog;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    BarChart barChart;
    AppCompatTextView textViewReminder;
    AppCompatTextView textViewReminder2;
    private Spinner spinner2,spinner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
       // addItemsOnSpinner2();
        addListenerOnSpinnerItemSelection();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setText("Overview"));
        tabLayout.addTab(tabLayout.newTab().setText("insights"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        barChart = (BarChart) findViewById(R.id.bargraph);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(54f, 0));
        barEntries.add(new BarEntry(29f, 1));
        barEntries.add(new BarEntry(77f, 2));
        barEntries.add(new BarEntry(99f, 3));
        barEntries.add(new BarEntry(19f, 4));
        barEntries.add(new BarEntry(62f, 5));


        BarDataSet barDataSet = new BarDataSet(barEntries, " todays  dates");

        ArrayList<String> theDates = new ArrayList<>();
        theDates.add("april");
        theDates.add("may");
        theDates.add("june");
        theDates.add("july");
        theDates.add("august");
        theDates.add("september");

        BarData thedata = new BarData(theDates, barDataSet);
        barChart.setData(thedata);
        barChart.setEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);
    }

    Calendar myCalender = Calendar.getInstance();

    private void updateLabe() {
        String myFormat = "dd MMMM ";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        textViewReminder.setText(sdf.format(myCalender.getTime()));
    }

    DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            myCalender.set(Calendar.YEAR, year);
            myCalender.set(Calendar.MONTH, monthOfYear);
            myCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabe();
        }
    };

    private void updateLabe2() {
        String myFormat = "dd MMMM -";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        textViewReminder2.setText(sdf.format(myCalender.getTime()));
    }

    DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            myCalender.set(Calendar.YEAR, year);
            myCalender.set(Calendar.MONTH, monthOfYear);
            myCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabe2();
        }
    };

    public void initView()
    {
        textViewReminder = (AppCompatTextView) findViewById(R.id.textViewReminder);
        textViewReminder2=(AppCompatTextView)findViewById(R.id.textViewReminder2);
        textViewReminder2.setOnClickListener(this);
        textViewReminder.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.textViewReminder:
                new DatePickerDialog(this, date1, myCalender.get(Calendar.YEAR)
                        , myCalender.get(Calendar.MONTH),
                        myCalender.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.textViewReminder2:
                new DatePickerDialog(this, date2, myCalender.get(Calendar.YEAR)
                        , myCalender.get(Calendar.MONTH),
                        myCalender.get(Calendar.DAY_OF_MONTH)).show();
                break;
        }

    }
    public void addListenerOnSpinnerItemSelection()
    {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }
}