package com.alphabank.codespace.src.view;

import com.alphabank.codespace.src.model.entity.Book;
import com.alphabank.codespace.src.service.WindowService;

import java.util.List;

public class SearchBookView {
  public void searchBooks(List<Book> books) {
    WindowService windowService = new WindowService();

    windowService.createSearchBookWindow(books, "Search Book");
  }
}
