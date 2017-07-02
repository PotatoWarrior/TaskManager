package com.example.dmko.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditItemActivity extends AppCompatActivity {
    private EditText editText;
    private Button saveBtn, deleteBtn;
    private DatabaseHelper databaseHelper;
    private String name;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        editText = (EditText) findViewById(R.id.editText);
        saveBtn = (Button) findViewById(R.id.saveBtn);
        deleteBtn = (Button) findViewById(R.id.deleteBtn);
        databaseHelper = new DatabaseHelper(this);
        Intent receivedIntent = getIntent();
        name = receivedIntent.getStringExtra("name");
        id = receivedIntent.getIntExtra("id", -1);

        editText.setText(name);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteItem(id);
                toastMessage("Item has been deleted");
                Intent intent = new Intent(EditItemActivity.this, ListDataActivity.class);
                startActivity(intent);
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = editText.getText().toString();
                if(!item.isEmpty()) {
                    databaseHelper.updateItem(id, item);
                    toastMessage("Item has been updated");
                }
                else toastMessage("Put something in the text field you fucking retard");
            }
        });
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
