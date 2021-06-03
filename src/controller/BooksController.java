package com.alphabank.codespace.src.controller;

import com.alphabank.codespace.src.service.BooksService;
import com.alphabank.codespace.src.view.BooksView;
import com.alphabank.codespace.src.view.AddBookView;
import com.alphabank.codespace.src.view.IncreasePriceView;
import com.alphabank.codespace.src.view.SearchBookView;
import javafx.stage.Stage;

public class BooksController {
  private BooksView booksView = new BooksView();
  private BooksService booksService = new BooksService();
  private AddBookView addBookView = new AddBookView();
  private SearchBookView searchBookView = new SearchBookView();
  private IncreasePriceView increasePriceView = new IncreasePriceView();

  public void showBooks() {
    booksView.showBooks(booksService.getBookList());
  }

  public void addBook() {
    addBookView.addBook();
  }

  public void searchBooks() {
    searchBookView.searchBooks(booksService.getBookList());
  }

  public void increasePrice() {
    increasePriceView.increasePrice(booksService.getBookList());
  }

  public void exit(Stage stage) {
    stage.close();
  }
}
