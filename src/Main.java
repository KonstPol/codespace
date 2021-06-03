package com.alphabank.codespace.src;

import com.alphabank.codespace.src.view.MainPage;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        MainPage mainPage = new MainPage(primaryStage);

        mainPage.createMainPage();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
