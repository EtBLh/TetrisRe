package com.assets.blocks;

import javafx.scene.image.Image;

public class blocksImgAccesser {

    public final Image[] blocks = new Image[7];

    public blocksImgAccesser() {
        for(int i = 1; i <=7; i++){
            blocks[i-1] = new Image(getClass().getResourceAsStream("b"+i+".png"));
        }
    }
}
