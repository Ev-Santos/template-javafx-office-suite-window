package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.Parent;

public class TelaApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        /*FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();*/


        Parent root = FXMLLoader.load(TelaApplication.class.getResource("Tela_principal.fxml"));

        stage.initStyle(javafx.stage.StageStyle.UNDECORATED); //stage.initStyle(javafx.stage.StageStyle.DECORATED); //stage.initStyle(javafx.stage.StageStyle.TRANSPARENT); //stage.initStyle(javafx.stage.StageStyle.UNIFIED); ///stage.initStyle(javafx.stage.StageStyle.UTILITY);
        stage.getIcons().add(new javafx.scene.image.Image( getClass().getResourceAsStream("img/app-icon.png") ));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}