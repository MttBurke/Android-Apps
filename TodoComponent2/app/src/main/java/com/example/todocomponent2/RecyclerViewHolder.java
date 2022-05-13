package com.example.todocomponent2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.todocomponent2.RoomDatabase.TodoViewModel;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder
{
    private TextView textTodo, textDetail;

    public RecyclerViewHolder(@NonNull View itemView)
    {
        super(itemView);

        textTodo = itemView.findViewById(R.id.main_Text);
        textDetail = itemView.findViewById(R.id.detail_Text);
    }

    public TextView getTextTodo()
    {
        return textTodo;
    }

    public TextView getTextDetail()
    {
        return textDetail;
    }

    public void bindName(String text)
    {
        textTodo.setText(text);
    }

    public void bindDetails(String text)
    {
        textDetail.setText(text);
    }

    static RecyclerViewHolder create(ViewGroup parent)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
        return new RecyclerViewHolder(view);
    }

}
