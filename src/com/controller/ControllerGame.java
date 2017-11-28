package com.controller;


import com.dto.GameDto;
import com.service.Service;
import com.service.gameAreaService;
import com.processer.processor;
import com.service.gameService;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerGame implements Initializable {

    public Canvas GameArea;
    public AnchorPane background;
    public Label scoreShower;
    public Label levelShower;
    public Label linesShower;
    public ImageView holdShower;
    public ImageView nextShower;

    /**
     * the GraphicsContext of Game Area
     */
    private GraphicsContext gameGC;
    /**
     * the data transfer object
     */
    private GameDto gameDto;
    /**
     * the service of game area
     */
    private gameAreaService gameAreaService;
    /**
     * the game service
     */
    private Service gameService;

    /**
     * game start
     */
    private void start(){
        gameService.start();
    }

    /*---------------------------------------------------------------------Key Controls-------------------------------------------------------------------------------------*/
    private boolean
            canWhirl = true,
            canHardDown = true,
            canHold = true;

    public void keySoftDrop() {
        gameService.ActDown();
    }

    public void keyLeft() {
        gameService.ActLeft();
    }

    public void keyRight() {
        gameService.ActRight();
    }

    public void keyWhirl() {
        if (!canWhirl) return;
        gameService.ActWhirl();
    }

    public void keyHardDown() {
        if (!canHardDown) return;
        gameService.ActHardDrop();
    }

    public void keyHold() {
        if (!canHold) return;
        gameService.HoldAct();
    }

    public void Pause() {
        gameService.ActDown();
    }

    public void setCanWhirl(boolean canWhirl) {
        this.canWhirl = canWhirl;
    }

    public void setCanHardDown(boolean canHardDown) {
        this.canHardDown = canHardDown;
    }

    public void setCanHold(boolean canHold) {
        this.canHold = canHold;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //initialize graphic context
        this.gameGC = GameArea.getGraphicsContext2D();
        //initialize dto
        this.gameDto = new GameDto();
        //initialize game area service
        this.gameAreaService = new gameAreaService(gameDto, gameGC);
        //initialize service
        this.gameService = new gameService(gameDto, gameAreaService);

        //set Showers
        this.gameService.setScoreShower(scoreShower);
        this.gameService.setLevelShower(levelShower);
        this.gameService.setLinesShower(linesShower);
        this.gameService.setNextShower(nextShower);
        this.gameService.setHoldShower(holdShower);

        this.start();
    }
}
