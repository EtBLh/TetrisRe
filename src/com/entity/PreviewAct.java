package com.entity;

import com.util.Point;

/**
 */
public class PreviewAct extends GameAct{
    public PreviewAct(int firstBlock) {
        super(firstBlock);
    }

    public void setActPoint(Point[] points){
        for (int i =0;i < actPoints.length;i++){
            this.actPoints[i].setLocation(points[i].getX(),points[i].getY());
        }
    }
}
