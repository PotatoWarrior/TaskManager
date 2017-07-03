package com.example.dmko.taskmanager.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dmko.taskmanager.Entity.Task;

import java.util.ArrayList;
import java.util.List;

public class SQLiteTaskDao extends SQLiteOpenHelper implements TaskDao {
    public static final String TABLE_NAME = "people_table";
    public static final String COL0 = "ID";
    public static final String COL1 = "name";
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + COL0 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL1 + " TEXT)";
    private static final String DELETE_TABLE = "DROP IF TABLE EXISTS " + TABLE_NAME;

    public SQLiteTaskDao(Context context) {
        super(context, TABLE_NAME, null, 1);
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
        this.getWritableDatabase().insert(TABLE_NAME, null, contentValues);
    }

    public List<Task> getAllTasks() {
        Cursor cursor = this.getWritableDatabase().rawQuery("SELECT * FROM " + TABLE_NAME, null);
        List<Task> result = new ArrayList<>(cursor.getCount());
        while (cursor.moveToNext()) {
            Task task = new Task();
            task.setId(cursor.getInt(0));
            task.setName(cursor.getString(1));
            result.add(task);
        }
        return result;
    }

    @Override
    public List<String> getAllTaskNames() {
        Cursor cursor = this.getWritableDatabase().rawQuery("SELECT * FROM " + TABLE_NAME, null);
        List<String> result = new ArrayList<>(cursor.getCount());
        while (cursor.moveToNext()) {
            result.add(cursor.getString(1));
        }
        return result;
    }

    public int getTaskIdByName(String name) {
        Cursor cursor = this.getWritableDatabase().rawQuery("SELECT " + COL0 + " FROM " + TABLE_NAME + " WHERE " + COL1 + " = '" + name + "'", null);
        cursor.moveToNext();
        return cursor.getInt(0);
    }

    public void updateTaskById(int id, Task newTask) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, newTask.getName());
        this.getWritableDatabase().update(TABLE_NAME, contentValues, COL0 + " = " + id, null);
    }

    public void deleteTaskById(int id) {
        this.getWritableDatabase().delete(TABLE_NAME, COL0 + " = " + id, null);
    }
}






















