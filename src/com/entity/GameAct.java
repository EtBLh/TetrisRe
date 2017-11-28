package com.entity;

import com.dto.GameDto;
import com.util.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 */
public class GameAct {
    /**
     * 方塊陣列
     */
    protected Point[] actPoints = null;

    private static List<Point[]> TYPE_CONFIG;

    static {
        TYPE_CONFIG = new ArrayList<>(7);
        TYPE_CONFIG.add(new Point[]{new Point(4, 0), new Point(3, 0), new Point(5, 0), new Point(4, 1)});
        TYPE_CONFIG.add(new Point[]{new Point(4, 0), new Point(3, 0), new Point(5, 0), new Point(4, 1)});
        TYPE_CONFIG.add(new Point[]{new Point(4, 1), new Point(4, 0), new Point(5, 0), new Point(3, 1)});
        TYPE_CONFIG.add(new Point[]{new Point(4, 0), new Point(3, 0), new Point(4, 1), new Point(5, 1)});
        TYPE_CONFIG.add(new Point[]{new Point(3, 0), new Point(4, 0), new Point(3, 1), new Point(4, 1)});
        TYPE_CONFIG.add(new Point[]{new Point(4, 0), new Point(3, 0), new Point(5, 0), new Point(3, 1)});
        TYPE_CONFIG.add(new Point[]{new Point(4, 0), new Point(3, 0), new Point(5, 0), new Point(5, 1)});
        TYPE_CONFIG.add(new Point[]{new Point(4, 0), new Point(3, 0), new Point(5, 0), new Point(6, 0)});
    }

    /**
     * 方塊種類
     */
    private int type;

    /**
     * 種類最大數
     */
    private final int MAX_TYPE = 7;

    public Point[] getActPoint() {
        return actPoints;
    }

    public int getType() {
        return type;
    }

    /**
     * 方塊移動
     *
     * @param moveX X軸偏移量
     * @param moveY Y軸偏移量
     */
    public boolean move(int moveX, int moveY, int[][] gameMap) {
        //移動處理
        for (int i = 0; i < actPoints.length; i++) {
            int newX = (int) (actPoints[i].getX() + moveX);
            int newY = (int) (actPoints[i].getY() + moveY);
            //判斷是否可以旋轉
            if (isOverZone(newX, newY, gameMap)) return false;
        }
        for (int i = 0; i < actPoints.length; i++) {
            int newX = (int) (actPoints[i].getX() + moveX);
            int newY = (int) (actPoints[i].getY() + moveY);
            actPoints[i].setLocation(newX, newY);
        }
        return true;
    }

    /**
     * 方塊移動至指定位置
     */
    public void moveTo(Point[] points) {
        for (int i =0 ; i < this.actPoints.length; i++){
            this.actPoints[i].setLocation(points[i].getX(),points[i].getY());
        }
    }

    /**
     * 方塊旋轉
     * <p>
     * 逆時針
     * A.x = O.y + O.x- B.y
     * A.y = O.y - O.x +B.x
     * <p>
     * 順時針
     * A.x = O.x - O.y + B.y
     * A.y = O.y + O.x - B.x
     */
    public void whirl(int[][] gameMap) {

        if (this.type == 4) return;

        for (int j = 0; j< 4; j++) {
            int isAllow = 0;
            Point[] points = new Point[4];
            //逆時針
            for (int i = 0; i < actPoints.length; i++) {
                if (i == j) {
                    points[i] = new Point((int)actPoints[i].getX(), (int)actPoints[i].getY());
                    continue;
                }
                int newX = (int) (actPoints[j].getX() + actPoints[j].getY() - actPoints[i].getY());
                int newY = (int) (actPoints[j].getY() - actPoints[j].getX() + actPoints[i].getX());
                //判斷是否可以旋轉
                if (isOverZone(newX, newY, gameMap)) break;
                points[i] = new Point(newX, newY);
                isAllow += 1;
            }
            if (isAllow == actPoints.length-1) {
                for (int i = 0; i < points.length; i++) {
                    actPoints[i].setLocation(points[i].getX(), points[i].getY());
                }
                return;
            }
        }

        for (int j = 0; j< 4; j++) {
            int isAllow = 0;
            Point[] points = new Point[4];
            //順時針
            for (int i = 0; i < actPoints.length; i++) {
                if (i == j) {
                    points[i] = new Point((int)actPoints[i].getX(), (int)actPoints[i].getY());
                    continue;
                }
                int newX = (int) (actPoints[0].getX() + actPoints[0].getY() - actPoints[i].getY());
                int newY = (int) (actPoints[0].getY() - actPoints[0].getX() + actPoints[i].getX());
                //判斷是否可以旋轉
                if (isOverZone(newX, newY, gameMap)) break;
                points[i] = new Point(newX, newY);
                isAllow += 1;
            }
            if (isAllow == actPoints.length) {
                for (int i = 0; i < points.length; i++) {
                    actPoints[i].setLocation(points[i].getX(), points[i].getY());
                }
            }
        }
    }

    /**
     * 判斷是否超出有效區域
     */
    private boolean isOverZone(int x, int y, int[][] gameMap) {
        return x < 0 || x > GameDto.GAMEZONE_W-1 || y < 0 || y > GameDto.GAMEZONE_H-1 || gameMap[x][y] > 0;
    }

    public void init(int type) {
        this.type = type;
        if (this.type == 0) this.type = new Random().nextInt(MAX_TYPE);
        Point[] points = TYPE_CONFIG.get(type);
        actPoints = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            actPoints[i] = new Point((int) points[i].getX(), (int) points[i].getY());
        }
    }

    public GameAct(int firstBlock) {
        init(firstBlock);
    }
}
