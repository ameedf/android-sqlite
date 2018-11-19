package com.ameed.sqlite.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.ameed.sqlite.data.Book.ID;
import static com.ameed.sqlite.data.Book.PRICE;
import static com.ameed.sqlite.data.Book.PUBLISH_DATE;
import static com.ameed.sqlite.data.Book.TABLE_NAME;
import static com.ameed.sqlite.data.Book.TITLE;
import static com.ameed.sqlite.data.BooksDbConstants.LOG_TAG;

public class BooksDbHelper extends SQLiteOpenHelper {

    public BooksDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LOG_TAG, "Creating all the tables");
        String createTableQuery = String.format(
                "CREATE TABLE %s(%s INTEGER PRIMARY KEY,%s TEXT,%s REAL,%s INTEGER)",
                TABLE_NAME, ID, TITLE, PRICE, PUBLISH_DATE);
        try {
            db.execSQL(createTableQuery);
        } catch (SQLiteException ex) {
            Log.e(LOG_TAG, "Create table exception: " +
                    ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        Log.w(LOG_TAG, String.format("Upgrading database from version %d to %d, which will destroy all old date", oldVersion, newVersion));
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
