package com.example.dmko.taskmanager.activity;

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

public class AddTaskActivity extends AppCompatActivity {
    private TaskDao taskDao;
    private EditText nameEditText, descriptionEditText;
    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        taskDao = new SQLiteTaskDao(this);
        nameEditText = (EditText) findViewById(R.id.nameEditTextA);
        descriptionEditText = (EditText) findViewById(R.id.descriptionEditTextA);
        addBtn = (Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task task = new Task();
                task.setName(nameEditText.getText().toString());
                task.setDescription(descriptionEditText.getText().toString());
                if (!task.getName().isEmpty()) {
                    taskDao.addTask(task);
                    clearFields();
                } else toastMessage("Populate the task name you fucking retard");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        clearFields();
    }

    private void clearFields(){
        nameEditText.setText("");
        descriptionEditText.setText("");
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
