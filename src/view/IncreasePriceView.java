package com.alphabank.codespace.src.view;

import com.alphabank.codespace.src.model.entity.Book;
import com.alphabank.codespace.src.service.WindowService;

import java.util.List;

public class IncreasePriceView {
  public void increasePrice(List<Book> books) {
    WindowService windowService = new WindowService();

    windowService.createIncreaseBookPriceWindow(books, "Increase Book Price");
  }
}
