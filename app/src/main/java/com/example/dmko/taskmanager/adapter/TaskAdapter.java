package com.example.dmko.taskmanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dmko.taskmanager.entity.Task;
import com.example.dmko.taskmanager.R;

import java.util.List;

public class TaskAdapter extends BaseAdapter {
    private Context context;
    private List<Task> tasks;
    private LayoutInflater layoutInflater;

    public TaskAdapter(Context context, List<Task> tasks) {
        this.context = context;
        this.tasks = tasks;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.task, parent, false);
        }
        TextView name = (TextView) view.findViewById(R.id.nameTextView);
        TextView description = (TextView) view.findViewById(R.id.descriptionTextView);

        Task task = (Task) getItem(position);
        name.setText(task.getName());
        description.setText(task.getDescription());
        return view;
    }
}
