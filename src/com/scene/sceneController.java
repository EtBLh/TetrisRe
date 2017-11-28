package com.scene;

import javafx.stage.Stage;

import java.io.IOException;

public class sceneController {
    private final sceneService service;
    /**
     * the main stage
     */
    private final Stage primaryStage;

    public void initScene() throws IOException{
        service.changeScene(1);
    }

    public void changeScene(int number) throws IOException{
        service.changeScene(number);
    }

    public sceneController(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.service = new sceneService(primaryStage);
    }
}
