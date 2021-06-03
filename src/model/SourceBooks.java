package com.alphabank.codespace.src.model;

import com.alphabank.codespace.src.model.entity.Book;
import com.alphabank.codespace.src.view.ErrorView;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class SourceBooks {
  private static final Logger logger = Logger.getLogger("BooksApp" + SourceBooks.class.getSimpleName());

  public static List<Book> generateBooks() {
    logger.info("Call \"generateBooks\" methods");
    File myFile = new File("books.json");
    List<Book> cloneBook = new ArrayList<Book>();
    Gson gson = new Gson();
    ErrorView errorView = new ErrorView();

    try {
      Book[] books = gson.fromJson(new FileReader(myFile), Book[].class);
      cloneBook = new ArrayList<Book>(Arrays.asList(books));
    } catch (IOException e) {
      errorView.createdErrorWindow("Файл не найден");
      logger.info("Get error in \"generateBooks\" method with error: " + e);
    } catch (NullPointerException e) {
      errorView.createdErrorWindow("Файл пустой");
      logger.info("Get error in \"generateBooks\" method with error: " + e);
    }

    return cloneBook;
  }
}