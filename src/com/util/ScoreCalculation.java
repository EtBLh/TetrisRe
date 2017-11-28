package com.util;

public class ScoreCalculation {
    public static int[] LINE_CLEARD_SCORE = new int[]{
        0,100,300,500,800
    };

    public static int getHardDownScore(int distance){
        return distance*2;
    }
}
