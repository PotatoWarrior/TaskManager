package com.example.dmko.taskmanager.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.dmko.taskmanager.adapter.TaskAdapter;
import com.example.dmko.taskmanager.entity.Task;
import com.example.dmko.taskmanager.R;
import com.example.dmko.taskmanager.dao.SQLiteTaskDao;
import com.example.dmko.taskmanager.dao.TaskDao;

public class ListTasksActivity extends AppCompatActivity {
    private ListView listView;
    private ImageButton addTaskButton;
    private TaskDao taskDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tasks);
        listView = (ListView) findViewById(R.id.listView);
        addTaskButton = (ImageButton) findViewById(R.id.addTaskBtn);
        taskDao = new SQLiteTaskDao(this);
        TaskAdapter taskAdapter = new TaskAdapter(this, taskDao.getAllTasks());
        listView.setAdapter(taskAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Task task = (Task) parent.getItemAtPosition(position);
                Intent intent = new Intent(ListTasksActivity.this, EditTaskActivity.class);
                intent.putExtra("task", task);
                startActivity(intent);
            }
        });
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListTasksActivity.this, AddTaskActivity.class);
                startActivity(intent);
            }
        });
    }
}
