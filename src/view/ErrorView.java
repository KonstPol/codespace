package com.alphabank.codespace.src.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ErrorView {
  public void createdErrorWindow(String errorMessage) {
    Stage errorStage = new Stage();
    BorderPane borderPane = new BorderPane();
    Group root = new Group();
    Text errorText;
    Button exitButton = new Button("OK");

    errorStage.setTitle("Error Message");
    errorStage.setScene(new Scene(root, 300, 200));
    borderPane.setMinWidth(300);
    borderPane.setMinHeight(200);

    errorText = new Text(errorMessage);
    borderPane.setCenter(errorText);
    BorderPane.setAlignment(exitButton, Pos.CENTER);
    BorderPane.setMargin(exitButton, new Insets(0,0,70,0)); // optional
    borderPane.setBottom(exitButton);
    root.getChildren().add(borderPane);

    exitButton.setOnMouseClicked(event -> {
      errorStage.close();
    });

    errorStage.show();
  }
}
