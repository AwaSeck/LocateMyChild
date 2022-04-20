package com.example.ouestmonenfant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class Database extends SQLiteOpenHelper {
    public static final String DBName = "Login.db";

    public Database(Context context) {
        super(context,"Login.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        /*
        Create 2 Tables in the database PARENT and ENFANT
         */
        myDB.execSQL("create Table Parent(username TEXT primary key, password TEXT, prenom TEXT, nom TEXT, email TEXT)");
        myDB.execSQL("create Table Enfant(username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int i, int i1) {
        myDB.execSQL("drop Table if exists Parent");
        myDB.execSQL("drop Table if exists Enfant");
    }

    // Insert data in PARENT table (for registering)
    public boolean insertData_parent(String username, String password, String prenom, String nom, String email, String userEnfant, String pswdEnfant){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("prenom", prenom);
        contentValues.put("nom", nom);
        contentValues.put("email", email);
        contentValues.put("userEnfant", userEnfant);
        contentValues.put("pswdEnfant", pswdEnfant);

        long result = myDB.insert("Parent",null,contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }

    // Insert data in ENFANT table
    public boolean insertData_enfant(String username, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);

        long result = myDB.insert("Enfant",null,contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }

    // To check if the username exists on the DB
    public boolean checkParentUserName(String username){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from Parent where username = ?", new String[] {username});
        if (cursor.getCount() > 0)
            return  true;
        else
            return false;
    }

    // To check if the password exists on the DB
    public boolean checkParentPassword(String username, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from Parent where username = ? and password = ?", new String[] {username,password});
        if (cursor.getCount() > 0)
            return  true;
        else
            return false;
    }

    /*
    We do the same for the children
     */

    // To check if the username exists on the DB
    public boolean checkEnfantUserName(String username){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from Enfant where username = ?", new String[] {username});
        if (cursor.getCount() > 0)
            return  true;
        else
            return false;
    }

    // To check if the password exists on the DB
    public boolean checkEnfantPassword(String username, String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from Enfant where username = ? and password = ?", new String[] {username,password});
        if (cursor.getCount() > 0)
            return  true;
        else
            return false;
    }
}
