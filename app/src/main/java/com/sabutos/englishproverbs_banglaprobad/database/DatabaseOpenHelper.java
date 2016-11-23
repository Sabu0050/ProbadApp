package com.sabutos.englishproverbs_banglaprobad.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sabutos.englishproverbs_banglaprobad.model.Proverbs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by devil on 15-Oct-16.
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "proverbs.sqlite";
    public static final String DBLOCATION = "/data/data/com.sabutos.englishproverbs_banglaprobad/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;


    public DatabaseOpenHelper(Context context) {
        super(context, DBNAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if (mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }

    public List<Proverbs> getListProverbs() {
        Proverbs proverbs = null;
        List<Proverbs> proverbsList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT ID,Proverb,GROUP_CONCAT(Probad,'।\n'),Flag FROM proverb_expression GROUP BY proverb ORDER BY proverb", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            proverbs = new Proverbs(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3));
            proverbsList.add(proverbs);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return proverbsList;
    }

    public List<Proverbs> getListProbad() {
        Proverbs proverbs = null;
        List<Proverbs> proverbsList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT ID,GROUP_CONCAT(Proverb,'.\n'),Probad,Flag FROM proverb_expression GROUP BY probad ORDER BY probad", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            proverbs = new Proverbs(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3));
            proverbsList.add(proverbs);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return proverbsList;
    }
    public List<Proverbs> getListProbadB() {
        Proverbs proverbs = null;
        List<Proverbs> proverbsList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT DISTINCT * FROM proverb_expression GROUP BY probad ORDER BY probad", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            proverbs = new Proverbs(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3));
            proverbsList.add(proverbs);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return proverbsList;
    }
    public List<Proverbs> getListProverbsEP() {
        Proverbs proverbs = null;
        List<Proverbs> proverbsList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT ID,GROUP_CONCAT(Proverb,'.\n'),GROUP_CONCAT(Probad,'।\n'),Flag FROM proverb_expression WHERE FLAG='1' GROUP BY proverb,probad ORDER BY proverb", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            proverbs = new Proverbs(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3));
            proverbsList.add(proverbs);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return proverbsList;
    }
}

