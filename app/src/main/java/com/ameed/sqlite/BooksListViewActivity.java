package com.ameed.sqlite;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ameed.sqlite.data.Book;
import com.ameed.sqlite.data.BooksHandler;

import java.util.List;
import java.util.stream.Collectors;

public class BooksListViewActivity extends ListActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        BooksHandler db = new BooksHandler(this);
        final List<Book> allBooks = db.getAllBooks();
        List<String> listAsStrings = allBooks
                .stream()
                .map(book -> book.getId() + ": " + book.getTitle())
                .collect(Collectors.toList());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listAsStrings);
        this.setListAdapter(adapter);
        ListView lv = getListView();
        lv.setOnItemClickListener((adapterView, view, i, l) -> {
            AlertDialog alert = new AlertDialog.Builder(this)
                    .setMessage("Selected item is: " + allBooks.get(i))
                    .create();
            alert.show();
        });
    }
}
