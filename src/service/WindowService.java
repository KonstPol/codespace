package com.alphabank.codespace.src.service;

import com.alphabank.codespace.src.exсeption.EmptyFieldException;
import com.alphabank.codespace.src.exсeption.NumberFieldException;
import com.alphabank.codespace.src.model.entity.Book;
import com.alphabank.codespace.src.view.ErrorView;
import com.google.gson.Gson;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class WindowService {
  private final Logger logger = Logger.getLogger("BooksApp" + this.getClass().getSimpleName());
  private BooksService booksService = new BooksService();
  private ErrorView errorView = new ErrorView();
  private ValidationService validationService = new ValidationService();

  public void createWindow(List<Book> books, String title) {
    Group root = new Group();
    Stage stage = new Stage();
    VBox vBox = new VBox();
    ScrollBar sc = new ScrollBar();
    Scene scene = new Scene(root, 500, 500);
    stage.setScene(scene);
    stage.setTitle(title);
    vBox.setMinWidth(stage.getMinWidth());
    vBox.setMinHeight(stage.getMinHeight());
    vBox.setSpacing(10);
    vBox.setPadding(new Insets(10));
    root.getChildren().addAll(vBox, sc);
    Text tableHeader = new Text(generateTableHeader());
    Text bookLabel;
    Button closeButton = new Button("Close Books List");

    vBox.getChildren().add(tableHeader);

    for (Book book: books) {
      bookLabel = new Text(convertBookToString(book));
      vBox.getChildren().add(bookLabel);
    }

    closeButton.setOnMouseClicked(event -> {
      stage.close();
    });

    vBox.getChildren().add(closeButton);

    sc.setLayoutX(scene.getWidth()-sc.getWidth());
    sc.setMin(0);
    sc.setOrientation(Orientation.VERTICAL);
    sc.setPrefHeight(500);
    sc.setMax(500);

    sc.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> ov,
                          Number old_val, Number new_val) {
        vBox.setLayoutY(-new_val.doubleValue());
      }
    });

    if (!validationService.isEmpty(booksService.getBookList())) {
      stage.show();
    }
  }

  public void createAddBookWindow() {
    Group root = new Group();
    GridPane gridPane = new GridPane();
    HBox hBox = new HBox();
    Stage displayBookStage = new Stage();

    displayBookStage.setTitle("Add Book");
    displayBookStage.setScene(new Scene(root, 490, 300));
    gridPane.setHgap(10);
    gridPane.setVgap(10);
    gridPane.setPadding(new Insets(10));
    hBox.setSpacing(10);

    //Author
    Label authorLabel = new Label("Author");
    TextField author = new TextField();

    gridPane.add(authorLabel, 0, 0);
    gridPane.add(author, 0, 1);

    //Title
    Label titleLabel = new Label("Title");
    TextField title = new TextField();

    gridPane.add(titleLabel, 1, 0);
    gridPane.add(title, 1, 1);

    //Publisher
    Label publisherLabel = new Label("Publisher");
    TextField publisher = new TextField();

    gridPane.add(publisherLabel, 2, 0);
    gridPane.add(publisher, 2, 1);

    //Year
    Label yearLabel = new Label("Year");
    TextField year = new TextField();

    gridPane.add(yearLabel, 0, 2);
    gridPane.add(year, 0, 3);

    //Page
    Label pagesLabel = new Label("Page");
    TextField pages = new TextField();

    gridPane.add(pagesLabel, 1, 2);
    gridPane.add(pages, 1, 3);

    //Cost
    Label costLabel = new Label("Cost");
    TextField cost = new TextField();

    gridPane.add(costLabel, 2, 2);
    gridPane.add(cost, 2, 3);

    Button addBookBtn = new Button("Add");
    Button clearBtn = new Button("Clear");
    Button exitBtn = new Button("Exit");

    hBox.getChildren().addAll(addBookBtn, clearBtn, exitBtn);
    gridPane.add(hBox, 0, 4);
    root.getChildren().add(gridPane);

    addBookBtn.setOnMouseClicked(event -> {
      addBook(author, title, publisher, year, pages, cost);
    });

    clearBtn.setOnMouseClicked(event -> {
      clear(author, title, publisher, year, pages, cost);
    });

    exitBtn.setOnMouseClicked(event -> {
      exit(displayBookStage);
    });

    displayBookStage.show();
  }

  public void createSearchBookWindow(List<Book> books, String title) {
    Group root = new Group();
    Stage stage = new Stage();
    VBox vBox = new VBox();
    ScrollBar sc = new ScrollBar();
    Text tableHeader = new Text(generateTableHeader());
    Text bookLabel;
    Label authorLabel = new Label("Search by author");
    Label yearLabel = new Label("Search by year");
    Label publisherLabel = new Label("Search by publisher");
    TextField authorField = new TextField();
    TextField yearField = new TextField();
    TextField publisher = new TextField();
    Button authorButton = new Button("Search by author");
    Button yearButton = new Button("Search by year");
    Button publisherButton = new Button("Search by publisher");
    Button closeButton = new Button("Close Books List");
    Scene scene = new Scene(root, 535, 500);

    stage.setScene(scene);
    stage.setTitle(title);

    vBox.setMinWidth(stage.getMinWidth());
    vBox.setMinHeight(stage.getMinHeight());
    vBox.setSpacing(10);
    vBox.setPadding(new Insets(10));
    vBox.getChildren().addAll(
      authorLabel,
      authorField,
      authorButton,
      yearLabel,
      yearField,
      yearButton,
      publisherLabel,
      publisher,
      publisherButton
    );

    root.getChildren().addAll(vBox, sc);

    closeButton.setOnMouseClicked(event -> {
      stage.close();
    });

    sc.setLayoutX(scene.getWidth()-sc.getWidth());
    sc.setMin(0);
    sc.setOrientation(Orientation.VERTICAL);
    sc.setPrefHeight(500);
    sc.setMax(500);

    sc.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
          vBox.setLayoutY(-new_val.doubleValue());
      }
    });

    if (validationService.isEmpty(booksService.getBookList())) {
      bookLabel = new Text("Nothing to show");
      vBox.getChildren().add(bookLabel);
    } else {
      vBox.getChildren().add(tableHeader);

      for (Book book: books) {
        bookLabel = new Text(convertBookToString(book));
        vBox.getChildren().add(bookLabel);
      }
    }

    vBox.getChildren().add(closeButton);

    stage.show();
  }

  public void createIncreaseBookPriceWindow(List<Book> books, String title) {
    Group root = new Group();
    Stage stage = new Stage();
    VBox vBox = new VBox();
    ScrollBar sc = new ScrollBar();
    Label authorLabel = new Label("Set percent");
    TextField percentField = new TextField();
    Button increaseButton = new Button("Increase");
    Button closeButton = new Button("Close Books List");
    Scene scene = new Scene(root, 535, 500);

    stage.setScene(scene);
    stage.setTitle(title);

    vBox.setMinWidth(stage.getMinWidth());
    vBox.setMinHeight(stage.getMinHeight());
    vBox.setSpacing(10);
    vBox.setPadding(new Insets(10));
    vBox.getChildren().addAll(
      authorLabel,
      percentField,
      increaseButton,
      closeButton
    );

    root.getChildren().addAll(vBox, sc);

    closeButton.setOnMouseClicked(event -> {
      stage.close();
    });

    increaseButton.setOnMouseClicked(event -> {
      increase(percentField);
    });
    sc.setLayoutX(scene.getWidth()-sc.getWidth());
    sc.setMin(0);
    sc.setOrientation(Orientation.VERTICAL);
    sc.setPrefHeight(500);
    sc.setMax(500);

    sc.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
          vBox.setLayoutY(-new_val.doubleValue());
      }
    });

    stage.show();
  }

  private void increase(TextField field) {
    try {
      try {
        validationService.chekFieldType(field, "Increase");

        if (!validationService.isFieldEmpty(field)) {
          File myFile = new File("books.json");
          Gson gson = new Gson();
          Book[] books = gson.fromJson(new FileReader(myFile), Book[].class);
          FileWriter fileWriter = new FileWriter(myFile);
          System.out.println(1);
          for (Book book: books) {
            book.setPrice(book.getPrice() * (Double.parseDouble(field.getText()) / 100));
          }

          String jsonInString = gson.toJson(books);

          fileWriter.write(jsonInString);
          fileWriter.close();
        }
      } catch (NumberFieldException e) {
        errorView.createdErrorWindow(e.toString().split(":")[1]);
        logger.info("Get error in \"increase\" method with error: " + e);
      }

    } catch (IOException e) {
      errorView.createdErrorWindow("Файл не найден");
      logger.info("Get error in \"addBlock\" method with error: " + e);
    }
  }

  private void addBook(TextField author, TextField title, TextField publisher, TextField year, TextField pages, TextField cost) {
    try {
      validationService.chekFieldsType(author, year, pages, cost);
    } catch (NumberFieldException e) {
      errorView.createdErrorWindow(e.toString().split(":")[1]);
      logger.info("Get error in \"addBook\" method with error: " + e);
    }

    try {
      validationService.checkFields(author, title, publisher, year, pages, cost);
    } catch (EmptyFieldException e) {
      errorView.createdErrorWindow(e.toString().split(":")[1]);
      logger.info("Get error in \"addBook\" method with error: " + e);
    }

    try {
      if (validationService.isFieldsEmpty(author, title, publisher, year, pages, cost)) {
        int yearValue = Integer.parseInt(String.valueOf(year.getText()));
        int pagesValue = Integer.parseInt(String.valueOf(pages.getText()));
        int costValue = Integer.parseInt(String.valueOf(cost.getText()));

        booksService.addBook(
          author.getText(),
          title.getText(),
          publisher.getText(),
          yearValue,
          pagesValue,
          costValue
        );
      }
    } catch (NumberFormatException e) {
      errorView.createdErrorWindow("Неправильный формат для числовых полей");
      logger.info("Get error in \"addBook\" method with error: " + e);
    }
  }

  private void clear(TextField ...args) {
    for (TextField field: args) {
      field.clear();
    }
  }

  private void exit(Stage stage) {
    stage.close();
  }

  private String convertBookToString(Book book) {
    return String.format(
      "| %-20s | %-30s | %-20s | %-5d | %-5d | %.2f |",
      book.getAuthor(), book.getTitle(), book.getPublisher(), book.getYear(), book.getPages(), book.getPrice()
    );
  }

  private String generateTableHeader() {
    return String.format(
      "| %-20s | %-30s | %-20s | %-5s | %-5s | %-5s |",
      "Author", "Title", "Publisher", "Year", "Pages", "Price"
    );
  }
}
