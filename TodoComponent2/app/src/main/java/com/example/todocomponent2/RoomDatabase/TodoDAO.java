package com.example.todocomponent2.RoomDatabase;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TodoDAO
{

    @Query("SELECT * FROM todo_database")
    LiveData<List<Todo_Data>> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Todo_Data todo_data);

    @Insert
    void insertAll(Todo_Data... todos);

    @Query("DELETE FROM TODO_DATABASE")
    void DeleteAll();

    @Delete
    void Delete(Todo_Data todo_data);

    @Update
    public void updateTodos(Todo_Data... todos);

}
