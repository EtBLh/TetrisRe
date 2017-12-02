package com.service;

import com.util.msgBox.MsgPane;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView; /**
 * the interface of a service of game
 */
public interface Service{

    //the act controls
    void ActWhirl();
    void ActDown();
    void ActLeft();
    void ActRight();
    void HoldAct();
    void ActHardDrop();

    //shower setters
    void setScoreShower(Label scoreShower);
    void setLevelShower(Label levelShower);
    void setLinesShower(Label linesShower);
    void setNextShower(ImageView nextShower);
    void setHoldShower(ImageView holdShower);
    void setMsgPane(MsgPane msgPane);

    void start();
}
