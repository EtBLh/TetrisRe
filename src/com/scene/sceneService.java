package com.scene;

import com.Main;
import com.controller.ControllerGame;
import com.playerControl.playerControlPressed;
import com.playerControl.playerControlReleased;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class sceneService {

    private final URL[] FXML_URLs = {
            Main.class.getResource("layout/Start.fxml"),
            Main.class.getResource("layout/Game.fxml")
    };

    private final int WINDOW_WIDTH = 1920;
    private final int WINDOW_HEIGHT = 1080;
    private FXMLLoader loader;

    public void changeScene(int number) throws IOException {
        loader = new FXMLLoader(FXML_URLs[number]);
        Parent root = loader.load();
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setScene(scene);

        //set Player Controls
        if(number == 1)playerControlSetting(scene);
    }

    private void playerControlSetting(Scene scene){
        ControllerGame controllerGame = loader.getController();
        scene.addEventFilter(KeyEvent.KEY_PRESSED, new playerControlPressed(controllerGame));
        scene.addEventFilter(KeyEvent.KEY_RELEASED, new playerControlReleased(controllerGame));
    }

    private final Stage primaryStage;

    public sceneService(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.initStyle(StageStyle.UNDECORATED);
        this.primaryStage.setAlwaysOnTop(true);
        this.primaryStage.setX(0);
        this.primaryStage.setY(0);
    }
}
