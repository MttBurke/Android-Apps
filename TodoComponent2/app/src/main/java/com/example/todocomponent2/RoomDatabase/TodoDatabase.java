package com.example.todocomponent2.RoomDatabase;

import android.content.Context;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Todo_Data.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverters.class})
public abstract class TodoDatabase extends RoomDatabase
{

    public abstract TodoDAO todoDAO();

    private static volatile TodoDatabase INSTANCE;
    private static final int NO_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NO_THREADS);

    static TodoDatabase getDatabase(final Context context)
    {
        if (INSTANCE == null)
        {
            synchronized (TodoDatabase.class)
            {
                if (INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TodoDatabase.class, "todo_database").addCallback(sRoomDatabaseCallBack).build();
                }
            }
        }

        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallBack = new RoomDatabase.Callback()
    {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db)
        {
            super.onCreate(db);

            databaseWriteExecutor.execute(() ->
            {
                TodoDAO dao = INSTANCE.todoDAO();
                dao.DeleteAll();

                Todo_Data todo_data = new Todo_Data("Wake Up");
                dao.insert(todo_data);
                todo_data = new Todo_Data("Eat Breakfast");
                dao.insert(todo_data);
            });
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db)
        {
            super.onOpen(db);

            databaseWriteExecutor.execute(() ->
            {
                TodoDAO dao = INSTANCE.todoDAO();

                Todo_Data todo_data = new Todo_Data("Wake Up");
                todo_data.setDetails("Wake up in the morning");
                todo_data.setIsComplete(false);
                dao.insert(todo_data);
                todo_data = new Todo_Data("Eat Breakfast");
                todo_data.setDetails("Cook and eat some breakfast");
                todo_data.setIsComplete(false);
                dao.insert(todo_data);
            });
        }
    };

}
