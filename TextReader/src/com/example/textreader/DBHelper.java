package com.example.textreader;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
      // ����������� �����������
      super(context, "myDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      Log.d("LOG_TAG", "--- onCreate database ---");
      // ������� ������� � ������
      db.execSQL("create table mytable ("
          + "id integer primary key autoincrement," 
          + "name text,"
          + "info text" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
  }