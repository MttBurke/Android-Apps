package com.example.todocomponent2.RoomDatabase;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.LiveData;

public class TodoRepository
{

    private TodoDAO todoDAO;
    private LiveData<List<Todo_Data>> allTodos;

    TodoRepository(Application application)
    {
        TodoDatabase database = TodoDatabase.getDatabase(application);
        todoDAO = database.todoDAO();
        allTodos = todoDAO.getAll();
    }

    LiveData<List<Todo_Data>> getAllTodos()
    {
        return allTodos;
    }

    void insert(Todo_Data todo_data)
    {
        TodoDatabase.databaseWriteExecutor.execute(() -> todoDAO.insertAll(todo_data));
    }

    void deleteAll()
    {
        TodoDatabase.databaseWriteExecutor.execute(() -> todoDAO.DeleteAll());
    }

    void update(Todo_Data todo_data)
    {
        TodoDatabase.databaseWriteExecutor.execute(() -> todoDAO.updateTodos(todo_data));
    }

}
