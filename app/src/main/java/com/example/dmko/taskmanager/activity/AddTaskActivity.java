package com.example.dmko.taskmanager.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dmko.taskmanager.Entity.Task;
import com.example.dmko.taskmanager.R;
import com.example.dmko.taskmanager.dao.SQLiteTaskDao;
import com.example.dmko.taskmanager.dao.TaskDao;

public class AddTaskActivity extends AppCompatActivity {
    private TaskDao taskDao;
    private EditText dataEditText;
    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        taskDao = new SQLiteTaskDao(this);
        dataEditText = (EditText) findViewById(R.id.dataEditText);
        addBtn = (Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task task = new Task();
                String name = dataEditText.getText().toString();
                task.setName(name);
                if (!task.getName().isEmpty()) {
                    taskDao.addTask(task);
                    dataEditText.setText("");
                } else toastMessage("Populate the task name you fucking retard");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        dataEditText.setText("");
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
