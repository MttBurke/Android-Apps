package com.example.todocomponent2;

import android.app.Application;

import com.example.todocomponent2.RoomDatabase.TodoViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class TodoViewModelFactory implements ViewModelProvider.Factory
{
    private Application mApplication;

    public TodoViewModelFactory(@NonNull Application application)
    {
        mApplication = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass)
    {
        return (T) new TodoViewModel(mApplication);
    }
}
