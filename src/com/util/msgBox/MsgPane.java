package com.util.msgBox;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.io.IOException;

public class MsgPane extends AnchorPane {
    public MsgPane() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MsgPane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        finally {
            if(!this.getShow()){
                this.setDisable(true);
                this.setVisible(false);
            }
        }
    }

    @FXML private Text msgTitle;
    @FXML private Text msgContent;
    @FXML private AnchorPane msgTitlePane;
    @FXML private TextField input;

    @FXML
    private void msgCloseButtonClicked() {
        this.setShow(false);
    }

    /**
     * fxml properties
     */

    public String getTitle() {
        return titleProperty().get();
    }

    public void setTitle(String value) {
        titleProperty().set(value);
    }

    public StringProperty titleProperty() {
        return msgTitle.textProperty();
    }

    BooleanProperty show = new SimpleBooleanProperty(false);

    public Boolean getShow() {
        return showProperty().get();
    }

    public void setShow(Boolean value) {
        showProperty().set(value);
        if(value)show();
        else close();
    }

    public BooleanProperty showProperty() {
        return show;
    }

    public String getInput() {
        return inputProperty().get();
    }

    public void setInput(String value) {
        inputProperty().set(value);
    }

    public StringProperty inputProperty() {
        return input.textProperty();
    }


    private void show(){

        this.setDisable(false);
        this.setVisible(true);

        //set fade in animation
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.3),this);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);


        //set move down animation
        TranslateTransition moveDown = new TranslateTransition(Duration.seconds(0.5),msgTitlePane);
        moveDown.setFromY(0);
        moveDown.setToY(800/2-150);

        //let move down and fade in animation play in a same time
        ParallelTransition parallelTransition = new ParallelTransition(moveDown, fadeIn);
        parallelTransition.play();
    }

    private void close(){
        //set fade in animation
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.3),this);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);


        //set move down animation
        TranslateTransition moveDown = new TranslateTransition(Duration.seconds(0.5),msgTitlePane);
        moveDown.setToY(800);

        //let move down and fade out  animation play in a same time
        ParallelTransition parallelTransition = new ParallelTransition(fadeOut,moveDown);
        parallelTransition.setOnFinished(e->{
            //setting of win panes
            this.setDisable(true);
            this.setVisible(false);
        });

        parallelTransition.play();
    }
}