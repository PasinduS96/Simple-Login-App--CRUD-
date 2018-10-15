package com.example.pasindusenarathne.testproject5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Pasindu Senarathne on 10/14/2018.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "userTEST";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_USER = "CREATE TABLE "+ User.UserDetails.TABLE_NAME+" (" +
            ""+User.UserDetails.T1COL1+" text PRIMARY KEY," +
            ""+User.UserDetails.T1COL2+" text," +
            ""+User.UserDetails.T1COL3+" text," +
            ""+User.UserDetails.T1COL4+" text," +
            ""+User.UserDetails.T1COL5+" text);";

    public DBHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_USER);
        Log.e("DATABSE OPERATION","DATA CREATED DATABSE");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+User.UserDetails.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void insertData(String id,String name,String password,String birth,String gender,SQLiteDatabase sqLiteDatabase){

        ContentValues contentValues = new ContentValues();
        contentValues.put(User.UserDetails.T1COL1,id);
        contentValues.put(User.UserDetails.T1COL2,name);
        contentValues.put(User.UserDetails.T1COL3,password);
        contentValues.put(User.UserDetails.T1COL4,birth);
        contentValues.put(User.UserDetails.T1COL5,gender);

        sqLiteDatabase.insert(User.UserDetails.TABLE_NAME,null,contentValues);
        Log.e("DATABSE OPERATION","DATA INSERTED ON DATABSE");
    }

    public void updateData(String id,String name,String password,String birth,String gender){

        ContentValues contentValues = new ContentValues();
        contentValues.put(User.UserDetails.T1COL2,name);
        contentValues.put(User.UserDetails.T1COL3,password);
        contentValues.put(User.UserDetails.T1COL4,birth);
        contentValues.put(User.UserDetails.T1COL5,gender);

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(User.UserDetails.TABLE_NAME,contentValues,"userid = ?",new String[]{id});
        Log.e("DATABSE OPERATION","DATA UPDATED ON DATABSE");

    }

    public void deleteData(String id){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(User.UserDetails.TABLE_NAME,"userid = ?",new String[]{id});
        Log.e("DATABSE OPERATION","DATA REMOVED FROM DATABSE");
    }

    public ArrayList<User> getDetails(String id){

        ArrayList<User> userDetails = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+User.UserDetails.TABLE_NAME+" WHERE "+User.UserDetails.T1COL1+" = '"+id+"'",null);

        while (cursor.moveToNext()){

            User user = new User();
            user.setId(cursor.getString(0));
            user.setName(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            user.setDateofbirth(cursor.getString(3));
            user.setGender(cursor.getString(4));
            userDetails.add(user);
        }

        return  userDetails;
    }

    public boolean isLogin(String username,String password){

        ArrayList<User> userDetails = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+User.UserDetails.TABLE_NAME+" WHERE "+User.UserDetails.T1COL2+" = '"+username+"' AND "+User.UserDetails.T1COL3+" = '"+password+"'",null);

        if(cursor.getCount() > 0){

            return  true;
        }
        return false;
    }

    public ArrayList<String> getIDList(){

        ArrayList<String> userIDList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT "+User.UserDetails.T1COL1+" FROM "+User.UserDetails.TABLE_NAME+"",null);

        while (cursor.moveToNext()){

            userIDList.add(cursor.getString(cursor.getColumnIndex(User.UserDetails.T1COL1)));
        }


        return userIDList;
    }
}
