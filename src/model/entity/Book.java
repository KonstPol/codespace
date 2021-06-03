package com.alphabank.codespace.src.model.entity;

public class Book {
  private String author;
  private String title;
  private String publisher;
  private int year;
  private int pages;
  private double price;

  public Book() {}

  public Book(String author, String title, String publisher, int year, int pages, int price) {
    this.author = author;
    this.title = title;
    this.publisher = publisher;
    this.year = year;
    this.pages = pages;
    this.price = price;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public int getPages() {
    return pages;
  }

  public void setPages(int pages) {
    this.pages = pages;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }
}
