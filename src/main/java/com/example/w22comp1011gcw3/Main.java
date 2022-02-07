package com.example.w22comp1011gcw3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("create-camera-view.fxml"));
        Scene scene = new Scene(FXMLLoader.load(Main.class.getResource("camera-tableview.fxml")));
        stage.setTitle("COMP1011 Camera SuperStore");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}