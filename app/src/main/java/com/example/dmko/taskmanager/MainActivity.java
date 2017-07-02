package com.example.dmko.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private EditText dataEditText;
    private Button addBtn, viewDataBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(this);
        dataEditText = (EditText) findViewById(R.id.dataEditText);
        addBtn = (Button) findViewById(R.id.addBtn);
        viewDataBtn = (Button) findViewById(R.id.viewDataBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = dataEditText.getText().toString();
                if (!data.isEmpty()) {
                    addData(data);
                    dataEditText.setText("");
                }
                else toastMessage("Put something in the text field you fucking retard");
            }
        });
        viewDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        dataEditText.setText("");
    }

    public void addData(String item) {
        boolean isDataAdded = databaseHelper.addData(item);
        if (isDataAdded) toastMessage("Data inserted successfully");
        else toastMessage("Something went wrong");
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
