package com.example.dmko.taskmanager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dmko.taskmanager.entity.Task;

import java.util.ArrayList;
import java.util.List;

public class SQLiteTaskDao extends SQLiteOpenHelper implements TaskDao {
    public static final String TABLE_NAME = "task_table";
    public static final String COL0 = "ID";
    public static final String COL1 = "name";
    public static final String COL2 = "description";
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + COL0 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL1 + " TEXT, " + COL2 + " TEXT)";
    private static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public SQLiteTaskDao(Context context) {
        super(context, TABLE_NAME, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE);
    }

    public void addTask(Task task) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, task.getName());
        contentValues.put(COL2, task.getDescription());
        this.getWritableDatabase().insert(TABLE_NAME, null, contentValues);
    }

    public List<Task> getAllTasks() {
        Cursor cursor = this.getWritableDatabase().rawQuery("SELECT * FROM " + TABLE_NAME, null);
        List<Task> result = new ArrayList<>(cursor.getCount());
        while (cursor.moveToNext()) {
            Task task = new Task();
            task.setId(cursor.getInt(0));
            task.setName(cursor.getString(1));
            task.setDescription(cursor.getString(2));
            result.add(task);
        }
        return result;
    }

    public void updateTaskById(int id, Task newTask) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, newTask.getName());
        contentValues.put(COL2, newTask.getDescription());
        this.getWritableDatabase().update(TABLE_NAME, contentValues, COL0 + " = " + id, null);
    }

    public void deleteTaskById(int id) {
        this.getWritableDatabase().delete(TABLE_NAME, COL0 + " = " + id, null);
    }
}






















