package com.example.todocomponent2;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.lifecycle.ViewModelProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todocomponent2.RoomDatabase.TodoViewModel;
import com.example.todocomponent2.RoomDatabase.Todo_Data;

import java.util.List;

public class Fragment_Todos extends Fragment
{

    private RecyclerView recyclerView;
    private TodoViewModel todoViewModel;
    private ListAdapter adapter;

    public Fragment_Todos()
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
        View v = inflater.inflate(R.layout.fragment_todos, container, false);

        recyclerView = v.findViewById(R.id.recyclerView);
        adapter = new ListAdapter(new ListAdapter.TodoDiff(), getParentFragmentManager());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));

        // Inflate the layout for this fragment
        return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        todoViewModel = new ViewModelProvider(this, new TodoViewModelFactory(getActivity().getApplication())).get(TodoViewModel.class);

        todoViewModel.getAllTodos().observe(getViewLifecycleOwner(), todo_data ->
        {
            adapter.submitList(todo_data);
        });
    }
}