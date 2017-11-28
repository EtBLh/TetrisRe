package com;

import com.scene.sceneController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        sceneController sceneManager = new sceneController(primaryStage);
        sceneManager.initScene();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
