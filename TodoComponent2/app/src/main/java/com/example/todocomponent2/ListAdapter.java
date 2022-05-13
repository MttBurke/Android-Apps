package com.example.todocomponent2;

import android.app.Application;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.todocomponent2.RoomDatabase.Todo_Data;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends androidx.recyclerview.widget.ListAdapter<Todo_Data, RecyclerViewHolder>
{

    private FragmentManager fragmentManager;

    public ListAdapter(@NonNull DiffUtil.ItemCallback<Todo_Data> todoDataItemCallback, FragmentManager fragmentManager)
    {
        super(todoDataItemCallback);
        this.fragmentManager = fragmentManager;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return RecyclerViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position)
    {
        Todo_Data current = getItem(position);
        holder.bindName(current.getName());
        holder.bindDetails(current.getDetails());
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DialogueFragment dialogueFragment = new DialogueFragment(current);
                dialogueFragment.show(fragmentManager, "DIALOGUE");
            }
        });
    }

    static class TodoDiff extends DiffUtil.ItemCallback<Todo_Data>
    {
        @Override
        public boolean areItemsTheSame(@NonNull Todo_Data oldItem, @NonNull Todo_Data newItem)
        {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Todo_Data oldItem, @NonNull Todo_Data newItem)
        {
            return oldItem.getName().equals(newItem.getName());
        }
    }

}
