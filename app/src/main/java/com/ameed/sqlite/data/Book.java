package com.ameed.sqlite.data;

import java.util.Date;

public class Book {
    public static final String ID = "id";
    public static final String TABLE_NAME = "books";
    public static final String TITLE = "title";
    public static final String PRICE = "price";
    public static final String PUBLISH_DATE = "publish_date";

    private Integer id;
    private String title;
    private Double price;
    private Date publishDate;

    public Book() {
    }

    public Book(String title, double price, Date publishDate) {
        this(null, title, price, publishDate);
    }

    public Book(Integer id, String title, double price, Date publishDate) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.publishDate = publishDate;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("\nid=%d\ntitle='%s'\nprice=%s\npublishDate=%s", id, title, price, publishDate);
    }
}
