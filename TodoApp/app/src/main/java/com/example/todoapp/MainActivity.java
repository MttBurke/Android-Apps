package com.example.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity
{

    private String[] todoArray;
    private int todoIndex = 0;
    public final String TODO_INDEX = "todoIndex";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();

        TextView todoText = findViewById(R.id.textView_Todo);

        todoArray = res.getStringArray(R.array.todos);
        todoText.setText(todoArray[todoIndex]);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = findViewById(R.id.tab_Layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_Label1));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label2));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label3));
        tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new
                TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
            viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {
            }
        });

        Button buttonNext = findViewById(R.id.button_Next);
        Button buttonPrev = findViewById(R.id.button_Prev);

        if (savedInstanceState != null)
        {
            todoIndex = savedInstanceState.getInt(TODO_INDEX);
            todoText.setText(todoArray[todoIndex]);
        }

        buttonNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                todoIndex = (todoIndex + 1) % todoArray.length;
                todoText.setText(todoArray[todoIndex]);
            }
        });

        buttonPrev.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (todoIndex == 0)
                {
                    todoIndex = todoArray.length - 1;
                }
                else
                {
                    todoIndex = (todoIndex - 1) % todoArray.length;
                }
                todoText.setText(todoArray[todoIndex]);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt(TODO_INDEX, todoIndex);
    }
}