package com.example.android_todo_first;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TodoActivity extends AppCompatActivity
{

    private String[] mTodos;
    private int mTodoIndex = 0;
    private static final String TODO_INDEX = "todoIndex";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        final TextView TodoTextView;
        TodoTextView = findViewById(R.id.textViewTodo);

        Resources res = getResources();
        mTodos = res.getStringArray(R.array.todos);

        TodoTextView.setText(mTodos[mTodoIndex]);

        Button buttonNext;
        buttonNext = findViewById(R.id.buttonNext);
        Button buttonPrev;
        buttonPrev = findViewById(R.id.buttonPrev);

        if (savedInstanceState != null)
        {
            mTodoIndex = savedInstanceState.getInt(TODO_INDEX, 0);
            TodoTextView.setText(mTodos[mTodoIndex]);
        }

        buttonNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (mTodoIndex < mTodos.length - 1)
                {
                    mTodoIndex++;
                }
                TodoTextView.setText(mTodos[mTodoIndex]);
            }
        });

        buttonPrev.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (mTodoIndex > 0)
                {
                    mTodoIndex--;
                }
                TodoTextView.setText(mTodos[mTodoIndex]);
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(TODO_INDEX, mTodoIndex);

    }
}