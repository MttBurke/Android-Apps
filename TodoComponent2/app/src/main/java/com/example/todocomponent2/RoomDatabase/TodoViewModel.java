package com.example.todocomponent2.RoomDatabase;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class TodoViewModel extends AndroidViewModel
{

    private TodoRepository todoRepository;

    private final LiveData<List<Todo_Data>> allTodos;


    public TodoViewModel(@NonNull Application application)
    {
        super(application);

        todoRepository = new TodoRepository(application);
        allTodos = todoRepository.getAllTodos();
    }

    public LiveData<List<Todo_Data>> getAllTodos()
    {
        return allTodos;
    }

    public void insert(Todo_Data todo_data)
    {
        todoRepository.insert(todo_data);
    }

    public void update(Todo_Data todo_data)
    {
        todoRepository.update(todo_data);
    }

    public void delete ()
    {
        todoRepository.deleteAll();
    }

}
