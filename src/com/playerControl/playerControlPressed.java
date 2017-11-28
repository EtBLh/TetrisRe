package com.playerControl;

import com.controller.ControllerGame;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import static com.util.keyList.SoftDrop;
import static com.util.keyList.Left;
import static com.util.keyList.Right;
import static com.util.keyList.Whirl;
import static com.util.keyList.HardDrop;
import static com.util.keyList.Hold;
import static com.util.keyList.Pause;


public class playerControlPressed implements EventHandler<KeyEvent> {

    private ControllerGame gameControl;

    public playerControlPressed(ControllerGame gameControl) {
        this.gameControl = gameControl;

    }

    /**
     * 鍵盤按下事件
     *
     * @param event
     */
    @Override
    public void handle(KeyEvent event) {
        if (event.getCode().toString().equals(SoftDrop)) gameControl.keySoftDrop();
        if (event.getCode().toString().equals(Left)) gameControl.keyLeft();
        if (event.getCode().toString().equals(Right)) gameControl.keyRight();
        if (event.getCode().toString().equals(Whirl)) gameControl.keyWhirl();
        if (event.getCode().toString().equals(HardDrop)) gameControl.keyHardDown();
        if (event.getCode().toString().equals(Hold)) gameControl.keyHold();
        if (event.getCode().toString().equals(Pause)) gameControl.Pause();
    }
}
