package com.example.todocomponent2;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todocomponent2.RoomDatabase.TodoViewModel;
import com.example.todocomponent2.RoomDatabase.Todo_Data;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;

public class DialogueFragment extends DialogFragment
{

    private Todo_Data todoData;
    public DialogueFragment(Todo_Data input)
    {
        todoData = input;
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
        setCancelable(true);
        getDialog().setTitle(todoData.getName());

        View v = inflater.inflate(R.layout.fragment_dialogue, container, false);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        TextView Details = v.findViewById(R.id.textView_Details);
        if (todoData.getDetails() != null)
        {
            Details.setText(todoData.getDetails());
        }
        else
        {
            Details.setText("No details specified");
        }

        TextView Date = v.findViewById(R.id.textView_Date);
        if (todoData.getEndDate() != null)
        {
            Date.setText(simpleDateFormat.format(todoData.getEndDate()));
        }
        else
        {
            Date.setText("No date specified");
        }

        CheckBox IsComplete = v.findViewById(R.id.checkBox_Complete);
        if (todoData.getIsComplete() != null)
        {
            IsComplete.setChecked(todoData.getIsComplete());
        }

        Button save = v.findViewById(R.id.buttonSave);

        save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getActivity(), "Todo Saved", Toast.LENGTH_SHORT).show();

                TodoViewModel todoViewModel = new ViewModelProvider(getActivity(), new TodoViewModelFactory(getActivity().getApplication())).get(TodoViewModel.class);
                todoData.setIsComplete(IsComplete.isChecked());
                todoViewModel.update(todoData);
            }
        });

        // Inflate the layout for this fragment
        return v;
    }
}