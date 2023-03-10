package com.techja.testonline.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.techja.testonline.models.Result;

import java.util.ArrayList;
import java.util.List;

public class DBHelperHistory extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "TestOnline";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_NAME = "history";

    private static final String KEY_ID = "id";
    private static final String KEY_MON_HOC = "monhoc";
    private static final String KEY_RESULT = "result";
    private static final String KEY_DATE = "date";


    public DBHelperHistory(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_students_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT,%s TEXT)", TABLE_NAME, KEY_ID, KEY_MON_HOC, KEY_RESULT, KEY_DATE);
        db.execSQL(create_students_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_students_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(drop_students_table);

        onCreate(db);
    }

    public void addResult(Result result) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATE, result.getDate());
        values.put(KEY_RESULT, result.getResult());
        values.put(KEY_MON_HOC, result.getMonHoc());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }


    public List<Result> getAllResult() {
        List<Result> studentList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME + "";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false) {
            Result student = new Result(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            studentList.add(student);
            cursor.moveToNext();
        }
        return studentList;
    }

}
