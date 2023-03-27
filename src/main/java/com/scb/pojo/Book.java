package com.scb.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
    int id;
    String name;
    String author;
    String publication;
    String category;
    long pages;
    double price;

    public void assertEquals(@NotNull Book book) {
        assertThat(String.format("book name in request: '%s', book name in response: '%s'",this.name, book.getName()),
                this.name, is(equalTo(book.getName())));
        assertThat(String.format("book author in request: '%s', book author in response: '%s'", this.author, book.getAuthor()),
                this.author, is(equalTo(book.getAuthor())));
        assertThat(String.format("book publication in request: '%s', book publication in response: '%s'", this.publication, book.getPublication()),
                this.publication, is(equalTo(book.getPublication())));
        assertThat(String.format("book category in request: '%s', book category in response: '%s'", this.category,  book.getCategory()),
                this.category, is(equalTo( book.getCategory())));
        assertThat(String.format("book pages in request: '%s', book pages in response: '%s'",this.pages, book.getPages()),
                this.pages, is(equalTo(book.getPages())));
        assertThat(String.format("book price in request: '%s', book price in response: '%s'", this.price, book.getPrice()),
                this.price, is(equalTo(book.getPrice())));

    }
}
