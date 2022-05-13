package com.example.todocomponent2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity
{

    private int pageNumber = 0;
    private final String PAGE_INDEX = "come.example.todocomponent2.PageIndex";
    private Fragment_Todos fragment_todos;
    private Edit_Fragment edit_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null)
        {
            pageNumber = savedInstanceState.getInt(PAGE_INDEX);
        }

        fragment_todos = new Fragment_Todos();
        edit_fragment = new Edit_Fragment();


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framelayout, fragment_todos);
        transaction.commit();

        Button button_Todos = findViewById(R.id.button_Todos);
        button_Todos.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.framelayout, fragment_todos);
                transaction.commit();
                pageNumber = 0;
            }
        });

        Button button_Edit = findViewById(R.id.button_Edit);
        button_Edit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.framelayout, edit_fragment);
                transaction.commit();

                pageNumber = 1;
            }
        });

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState)
    {
        outState.putInt(PAGE_INDEX, pageNumber);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState)
    {

        pageNumber = savedInstanceState.getInt(PAGE_INDEX);
        if (pageNumber == 0)
        {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.framelayout, fragment_todos);
            transaction.commit();
        }
        else
        {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.framelayout, edit_fragment);
            transaction.commit();
        }

    }
}