package com.ameed.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ameed.sqlite.data.Book;
import com.ameed.sqlite.data.BooksHandler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BooksHandler db = new BooksHandler(this);
        db.deleteAllBooks();
        insertBooks(db);

        final Button btn = findViewById(R.id.showBooksBtn);
        btn.setOnClickListener(view -> {
            Intent intent = new Intent(this, BooksListViewActivity.class);
            startActivity(intent);
        });
    }

    private void insertBooks(BooksHandler db) {
        Log.d("Inserting:", "Inserting books to DB");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            db.addBook(new Book("Harry Potter", 8.7, dateFormat.parse("9/8/1999")));
            db.addBook(new Book("The Secret Life of Bees", 9.6, dateFormat.parse("3/2/2009")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}
