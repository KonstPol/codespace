package com.alphabank.codespace.src.service;

import com.alphabank.codespace.src.model.SourceBooks;
import com.alphabank.codespace.src.model.entity.Book;
import com.alphabank.codespace.src.view.ErrorView;
import com.google.gson.Gson;
import java.util.logging.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BooksService {
  private List<Book> bookList;
  private final Logger logger = Logger.getLogger("BooksApp" + this.getClass().getSimpleName());

  public List<Book> getBookList() {
    logger.info("Call \"getBookList\" method");
    this.bookList = SourceBooks.generateBooks();

    return (List<Book>) (new ArrayList<Book>(bookList));
  }

  public void addBook(String author, String title, String publisher, int year, int pages, int price) {
    logger.info("Call \"addBook\" method");
    File myFile = new File("books.json");
    ErrorView errorView = new ErrorView();
    Gson gson = new Gson();

    try {
      Book[] books = gson.fromJson(new FileReader(myFile), Book[].class);
      List<Book> cloneBook = new ArrayList(Arrays.asList(books));
      FileWriter fileWriter = new FileWriter(myFile);

      cloneBook.add(new Book(author, title, publisher, year, pages, price));
      String jsonInString = gson.toJson(cloneBook);

      fileWriter.write(jsonInString);
      fileWriter.close();
    } catch (IOException e) {
      errorView.createdErrorWindow("Файл не найден");
      logger.info("Get error in \"addBlock\" method with error: " + e);
    }
  }
}

