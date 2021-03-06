package com.example.dmko.taskmanager.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dmko.taskmanager.entity.Task;
import com.example.dmko.taskmanager.R;
import com.example.dmko.taskmanager.dao.SQLiteTaskDao;
import com.example.dmko.taskmanager.dao.TaskDao;

public class EditTaskActivity extends AppCompatActivity {
    private EditText nameEditText, descriptionEditText;
    private Button saveBtn, deleteBtn;
    private TaskDao taskDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        nameEditText = (EditText) findViewById(R.id.nameEditTextE);
        descriptionEditText = (EditText) findViewById(R.id.descriptionEditTextE);
        saveBtn = (Button) findViewById(R.id.saveBtn);
        deleteBtn = (Button) findViewById(R.id.deleteBtn);
        taskDao = new SQLiteTaskDao(this);

        Intent receivedIntent = getIntent();
        final Task task = (Task) receivedIntent.getSerializableExtra("task");
        setFields(task);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskDao.deleteTaskById(task.getId());
                toastMessage("Task has been deleted");
                Intent intent = new Intent(EditTaskActivity.this, ListTasksActivity.class);
                startActivity(intent);
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskName = nameEditText.getText().toString();
                String taskDescription = descriptionEditText.getText().toString();
                Task newTask = new Task(taskName, taskDescription);
                if(!newTask.getName().isEmpty() && !newTask.getDescription().isEmpty()) {
                    taskDao.updateTaskById(task.getId(), newTask);
                    toastMessage("Task has been updated");
                }
                else toastMessage("Put something in the text field you fucking retard");
            }
        });
    }

    private void setFields(Task task){
        nameEditText.setText(task.getName());
        descriptionEditText.setText(task.getDescription());
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
