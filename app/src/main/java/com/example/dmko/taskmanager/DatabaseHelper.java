package com.example.dmko.taskmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "people_table";
    public static final String COL0 = "ID";
    public static final String COL1 = "name";
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + COL0 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL1 + " TEXT)";
    private static final String DELETE_TABLE = "DROP IF TABLE EXISTS " + TABLE_NAME;

    public DatabaseHelper(Context context) {
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

    public boolean addData(String item) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, item);
        long result = this.getWritableDatabase().insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public List<String> getData() {
        Cursor cursor =  this.getWritableDatabase().rawQuery("SELECT * FROM " + TABLE_NAME, null);
        List<String> result = new ArrayList<>(cursor.getCount());
        while(cursor.moveToNext()){
            result.add(cursor.getString(1));
        }
        return result;
    }

    public int getId(String item){
        Cursor cursor = this.getWritableDatabase().rawQuery("SELECT " + COL0 + " FROM " + TABLE_NAME + " WHERE " + COL1 + " = '" + item + "'", null);
        cursor.moveToNext();
        return cursor.getInt(0);
    }

    public void updateItem(int id, String newName){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, newName);
        this.getWritableDatabase().update(TABLE_NAME, contentValues, COL0 + " = " + id, null);
    }

    public void deleteItem(int id){
        this.getWritableDatabase().delete(TABLE_NAME, COL0 + " = " + id, null);
    }
}






















