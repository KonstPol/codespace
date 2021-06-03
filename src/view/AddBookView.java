package com.alphabank.codespace.src.view;

import com.alphabank.codespace.src.service.WindowService;

public class AddBookView {
  public void addBook() {
    WindowService windowService = new WindowService();

    windowService.createAddBookWindow();
  }
}
