package com.util;

import javafx.scene.input.KeyCode;

public interface keyList {
    String
            Whirl = KeyCode.UP.toString(),
            SoftDrop = KeyCode.DOWN.toString(),
            Left  = KeyCode.LEFT.toString(),
            Right = KeyCode.RIGHT.toString(),
            HardDrop = KeyCode.SPACE.toString(),
            Hold = KeyCode.C.toString(),
            Pause  = KeyCode.ESCAPE.toString();
}
