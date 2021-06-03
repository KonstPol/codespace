package com.alphabank.codespace.src.view;

import com.alphabank.codespace.src.model.entity.Book;
import com.alphabank.codespace.src.service.WindowService;

import java.util.List;

public class BooksView {
  public void showBooks(List<Book> books) {
    WindowService windowService = new WindowService();

    windowService.createWindow(books, "Books List");
  }
}
