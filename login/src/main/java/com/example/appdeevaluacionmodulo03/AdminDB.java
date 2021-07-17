package com.example.appdeevaluacionmodulo03;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AdminDB extends SQLiteOpenHelper {

    public AdminDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
    super(context, name, factory, version);
    }
    //Se crea la tabla a utilizar
    @Override
    public void onCreate(SQLiteDatabase db) {

    db.execSQL("create table GastosEnero (codigo int primary key, mes, gasto)");

    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
