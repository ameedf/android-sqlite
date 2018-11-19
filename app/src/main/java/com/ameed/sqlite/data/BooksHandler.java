package com.ameed.sqlite.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.ameed.sqlite.data.BooksDbConstants.DATABASE_NAME;
import static com.ameed.sqlite.data.BooksDbConstants.DATABASE_VERSION;
import static com.ameed.sqlite.data.BooksDbConstants.LOG_TAG;

public class BooksHandler {
    private BooksDbHelper dbHelper;

    public BooksHandler(Context context) {
        dbHelper = new BooksDbHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public List<Book> getAllBooks() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(Book.TABLE_NAME, null, null, null, null, null, null);
        List<Book> books = new ArrayList<>();
        while (cursor.moveToNext()) {
            Book book = toBook(cursor);
            books.add(book);
        }
        return books;
    }

    public Book getBook(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Book book = null;
        Cursor cursor = db.query(Book.TABLE_NAME, null, Book.ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        // Check if the book was found
        if (cursor.moveToFirst()) {
            book = toBook(cursor);
        }
        return book;
    }

    @NonNull
    private Book toBook(Cursor cursor) {
        Integer id = cursor.getInt(0);
        String title = cursor.getString(1);
        double price = cursor.getDouble(2);
        Date date = new Date(cursor.getLong(3));
        return new Book(id, title, price, date);
    }


    public void addBook(Book book) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues newBookValues = new ContentValues();
        newBookValues.put(Book.TITLE, book.getTitle());
        newBookValues.put(Book.PRICE, book.getPrice());
        newBookValues.put(Book.PUBLISH_DATE, book.getPublishDate().getTime());
        try {
            // Inserting the new row, or throwing an exception if an error occurred
            db.insertOrThrow(Book.TABLE_NAME, null, newBookValues);
        } catch (SQLiteException ex) {
            Log.e(LOG_TAG, ex.getMessage());
            throw ex;
        } finally {
            db.close();
        }
    }

    public void updateBookPrice(Book book) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(Book.PRICE, book.getPrice());

            db.update(Book.TABLE_NAME, values, Book.ID + "?=", new String[]{String.valueOf(book.getId())});
        } catch (SQLiteException ex) {
            Log.e(LOG_TAG, ex.getMessage());
            throw ex;
        } finally {
            db.close();
        }
    }

    public void deleteBook(Book book) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.delete(Book.TABLE_NAME, Book.ID + "?=", new String[]{String.valueOf(book.getId())});
        } catch (SQLiteException ex) {
            Log.e(LOG_TAG, ex.getMessage());
            throw ex;
        } finally {
            db.close();
        }
    }

    public void deleteAllBooks() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.execSQL("delete from " + Book.TABLE_NAME);
        } catch (SQLiteException ex) {
            Log.e(LOG_TAG, ex.getMessage());
            throw ex;
        } finally {
            db.close();
        }
    }
}
