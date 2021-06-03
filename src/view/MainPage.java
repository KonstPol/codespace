package com.alphabank.codespace.src.view;

import com.alphabank.codespace.src.controller.BooksController;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainPage {
  Stage primaryStage;

  public MainPage(Stage primaryStage) {
    this.primaryStage = primaryStage;
  }

  public void createMainPage() {
    BooksController booksController = new BooksController();
    HBox hBox = new HBox();
    Group root = new Group();
    Button showBookListBtn = new Button("Get Books List");
    Button addBookBtn = new Button("Add Book");
    Button searchBookBtn = new Button("Search Books");
    Button increasePriceBtn = new Button("Increase Book Price");
    Button exitBtn = new Button("Exit From App");

    //main settings
    primaryStage.setTitle("Books App (Menu)");
    primaryStage.setScene(new Scene(root, 517, 300));

    //layout settings
    hBox.setMinWidth(primaryStage.getMinWidth());
    hBox.setMinHeight(primaryStage.getMinHeight());
    hBox.setSpacing(10);
    hBox.setPadding(new Insets(10));
    hBox.getChildren().addAll(showBookListBtn, addBookBtn, searchBookBtn, increasePriceBtn, exitBtn);
    root.getChildren().add(hBox);

    //event handling
    showBookListBtn.setOnMouseClicked(event -> {
      booksController.showBooks();
    });

    addBookBtn.setOnMouseClicked(event -> {
      booksController.addBook();
    });

    searchBookBtn.setOnMouseClicked(event -> {
      booksController.searchBooks();
    });

    increasePriceBtn.setOnMouseClicked(event -> {
      booksController.increasePrice();
    });

    exitBtn.setOnMouseClicked(event -> {
      booksController.exit(primaryStage);
    });

    //mount scene
    primaryStage.show();
  }
}
