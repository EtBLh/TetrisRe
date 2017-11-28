package com.service;

import com.dto.GameDto;
import com.util.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class gameAreaService {

    /**
     * the space of every block
     */
    private final int SPACING = 2;
    /**
     * the length of the every block
     */
    private final int BLOCK_LENGTH = 50;

    /**
     * 遊戲數據
     */
    private GameDto gameDto;
    private GraphicsContext GC;

    public gameAreaService(GameDto gameDto, GraphicsContext GC) {
        this.gameDto = gameDto;
        this.GC = GC;
    }

    private Color[] colors = new Color[]{
            Color.rgb(255, 255, 255),
            Color.rgb(157, 70, 153), //  purple
            Color.rgb(233, 69, 107), //red
            Color.rgb(189, 215, 67), //green
            Color.rgb(247, 239, 77), //yellow
            Color.rgb(241, 153, 54), //orange
            Color.rgb(115, 199, 163), //blue mix green
            Color.rgb(89, 199, 244) //blue
    };

    /**
     * draw the
     */
    private void drawPreviewAct() {
        Point[] points;
        points = this.gameDto.getPreviewAct().getActPoint();
        for (Point point : points) {
            if (point.getY() <= 1) return;
        }
        for (Point point : points) {
            drawBlock(point.getX() * BLOCK_LENGTH,  + (point.getY() - 2) * BLOCK_LENGTH, gameDto.getGameAct().getType(), 0.5);
        }
    }

    /**
     * draw the downing blocks
     */
    private void drawRealAct() {
        Point[] points;
        points = this.gameDto.getGameAct().getActPoint();
        for (Point point : points) {
            if (point.getY() < 2) continue;
            drawBlock(point.getX() * BLOCK_LENGTH,  + (point.getY() - 2) * BLOCK_LENGTH, gameDto.getGameAct().getType(), this.gameDto.isGhostAct() ? 0.7 : 1);
        }
    }

    private void drawBlock(double x, double y, int color, double opacity) {
        if (color == 0) {
            return;
        }
        //設定方塊顏色
        GC.setFill(colors[color]);
        //設定不透明度
        GC.setGlobalAlpha(opacity);
        //畫方塊
        GC.fillRect(x + SPACING, y + SPACING, BLOCK_LENGTH - SPACING, BLOCK_LENGTH - SPACING);
        //設回不透明度
        GC.setGlobalAlpha(1);
    }

    private void PrintMap() {
        //get gameMap
        int[][] GameMap = this.gameDto.getGameMap();
        //draw Map
        for (int i = 0; i < GameMap.length; i++) {
            Con:
            for (int j = 2; j < GameMap[0].length; j++) {
                for (int n : this.gameDto.getclearLinesAnimationStatus()) {
                    if (n == j) {
                        drawBlock(i * BLOCK_LENGTH, + (j - 2) * BLOCK_LENGTH, GameMap[i][j], 0.5);
                        continue Con;
                    }
                }
                drawBlock(i * BLOCK_LENGTH,  + (j - 2) * BLOCK_LENGTH, GameMap[i][j], 1);
            }
        }
    }

    /**
     * repaint the game area
     */
    public void repaint(){
        GC.clearRect(0,0,500,1000);
        this.PrintMap();
        this.drawRealAct();
        this.drawPreviewAct();
    }
}
