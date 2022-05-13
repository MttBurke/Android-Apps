package com.example.hellocounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private int mCount = 0;
    private TextView mShowCount;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mShowCount = findViewById(R.id.show_count);
    }

    public void showMessage(View view)
    {
        Toast.makeText(this, R.string.show_message, Toast.LENGTH_SHORT).show();
    }

    public void CountUp(View view)
    {
        mCount++;
        if (mShowCount != null)
        {
            mShowCount.setText(Integer.toString(mCount));
        }
    }
}