package com.example.todocomponent2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todocomponent2.RoomDatabase.TodoViewModel;
import com.example.todocomponent2.RoomDatabase.Todo_Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.content.ContentValues.TAG;

public class Edit_Fragment extends Fragment
{

    private static final String EXTRA_REPLY = "Reply";
    private Date EndDate;
    private EditText Todo;
    private EditText Details;
    private Button Save;
    private TextView DateText;
    TodoViewModel todoViewModel;

    public Edit_Fragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View v = inflater.inflate(R.layout.fragment_edit, container, false);

        Todo = v.findViewById(R.id.editTextTodoName);
        Details = v.findViewById(R.id.editTextDetails);
        DateText = v.findViewById(R.id.textViewDate);

        todoViewModel = new ViewModelProvider(this, new TodoViewModelFactory(getActivity().getApplication())).get(TodoViewModel.class);

        v.findViewById(R.id.button_Date).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.show(getParentFragmentManager(), "datePicker");
            }
        });
        Save = v.findViewById(R.id.button_Save);
        Save.setOnClickListener(view ->
        {
            if (TextUtils.isEmpty(Todo.getText()))
            {
                Toast.makeText(getContext(), R.string.todo_invalid, Toast.LENGTH_LONG);
            }
            else
            {
                Todo_Data todo = new Todo_Data(Todo.getText().toString());
                todo.setDetails(Details.getText().toString());
                todo.setIsComplete(false);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                try
                {
                    Date date = simpleDateFormat.parse(DateText.getText().toString());
                    todo.setEndDate(date);
                }
                catch (ParseException e)
                {
                    Log.e(TAG, "onCreateView: Error Parsing Date");
                }

                todoViewModel.insert(todo);
            }
        });

        Button Delete = v.findViewById(R.id.button_Delete);

        Delete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                todoViewModel.delete();

                Toast.makeText(getActivity(), "All Todos have been deleted", Toast.LENGTH_LONG).show();
            }
        });

        // Inflate the layout for this fragment
        return v;
    }

}