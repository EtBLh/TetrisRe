package com.playerControl;

import com.controller.ControllerGame;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import static com.util.keyList.Whirl;
import static com.util.keyList.HardDrop;
import static com.util.keyList.Hold;

/**
 */
public class playerControlReleased implements EventHandler<KeyEvent> {

    private ControllerGame gameControl;

    public playerControlReleased(ControllerGame gameControl) {
        this.gameControl = gameControl;
    }

    @Override
    public void handle(KeyEvent event) {
        if (event.getCode().toString().equals(Whirl)) gameControl.setCanWhirl(true);
        if (event.getCode().toString().equals(HardDrop)) gameControl.setCanHardDown(true);
        if (event.getCode().toString().equals(Hold)) gameControl.setCanHold(true);
    }
}
