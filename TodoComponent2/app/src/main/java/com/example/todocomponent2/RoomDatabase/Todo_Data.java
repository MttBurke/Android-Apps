package com.example.todocomponent2.RoomDatabase;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = "todo_database")
public class Todo_Data
{

    @PrimaryKey
    @NonNull
    private String Name;

    private String Details;

    private Boolean IsComplete;

    @TypeConverters({DateConverters.class})
    private Date EndDate;

    public Todo_Data(@NonNull String Name)
    {
        this.Name = Name;
    }

    public void setName(String Name)
    {
        this.Name = Name;
    }

    public String getName()
    {
        return Name;
    }

    public void setDetails(String Details)
    {
        this.Details = Details;
    }

    public String getDetails()
    {
        return Details;
    }

    public void setEndDate(Date EndDate)
    {
        this.EndDate = EndDate;
    }

    public Date getEndDate()
    {
        return EndDate;
    }

    public void setIsComplete(Boolean IsComplete)
    {
        this.IsComplete = IsComplete;
    }

    public Boolean getIsComplete()
    {
        return IsComplete;
    }
}
