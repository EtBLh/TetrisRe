package com.dto;


import com.entity.GameAct;
import com.entity.PreviewAct;
import com.util.Point;
import java.util.ArrayList;

public class GameDto {

    /**
     * 遊戲高度和寛度
     */
    public static final int GAMEZONE_W = 10;
    public static final int GAMEZONE_H = 22;

//TODO
//    /**
//     * 本地數據控制
//     */
//    Data localData;
//    /**
//        *  本地記錄
//        */
//    private List<Player> diskRecord;
    /**
        * 遊戲地圖
        */
    private int[][] gameMap;
    /**
        * 下落方塊
        */
    private GameAct gameAct;
    /**
     * 預覽下落方塊
     */
    private PreviewAct previewAct = new PreviewAct(0);
    /**
        * 下一個方塊
        */
    private int nextBlock;
    /**
     * 等級
     */
    private int level = 1;
    /**
     * 分數
     */
    private int Score = 0;
    /**
     * 已刪行數
     */
    private int clearedLines = 0;
    /**
     * 保持方塊
     */
    private int holdBlock;
    /**
     *  消行動畫狀態
     */
    private ArrayList<Integer> clearLinesAnimationStatus = new ArrayList<Integer>();
    /**
     * 是否暫停
     */
    private boolean isPause;
    /**
     * 是否跳過下降方塊
     */
    private boolean isSkipConcreting;
    /**
     * 方塊虛化
     */
    private boolean GhostAct;
    /**
     * 自動下降預設延遲 (ms)
     */
    private int defaultDalay = 1000;
    /**
     * 是否延遲下降
     */
    private boolean isDownDelay = false;

    public boolean isDownDelay() {
        return isDownDelay;
    }

    public void setDownDelay(boolean downDelay) {
        isDownDelay = downDelay;
    }

    public int getDefaultDalay() {
        return defaultDalay;
    }

    public boolean isGhostAct() {
        return GhostAct;
    }

    public void setGhostAct(boolean ghostAct) {
        GhostAct = ghostAct;
    }

    public boolean isSkipConcreting() {
        return isSkipConcreting;
    }

    public void setSkipConcreting(boolean skipConcreting) {
        isSkipConcreting = skipConcreting;
    }

    public ArrayList<Integer> getclearLinesAnimationStatus() {
        return clearLinesAnimationStatus;
    }

    public void setClearLinesAnimationStatus(ArrayList<Integer> clearLinesAnimationStatus) {
        this.clearLinesAnimationStatus = clearLinesAnimationStatus;
    }

    public GameAct getPreviewAct() {
        return previewAct;
    }

    public void setPreviewAct(int type, Point[] points) {
        previewAct = new PreviewAct(type);
        previewAct.setActPoint(points);
    }

    public int getHoldBlock() {
        return holdBlock;
    }

    public void setHoldBlock(int holdBlock) {
        this.holdBlock = holdBlock;
    }

    public int[][] getGameMap() {
        return gameMap;
    }

    public void setGameMap(int[][] gameMap) {
        this.gameMap = gameMap;
    }

    public GameAct getGameAct() {
        return gameAct;
    }

    public void setGameAct(GameAct gameAct) {
        this.gameAct = gameAct;
    }

    public int getNextBlock() {
        return nextBlock;
    }

    public void setNextBlock(int nextBlock) {
        this.nextBlock = nextBlock;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int Score) {
        this.Score = Score;
    }

    public int getClearedLines() {
        return clearedLines;
    }

    public void setRemovedLines(int removeLine) {
        this.clearedLines = removeLine;
    }

    public GameDto() {
        dtoInit();
    }

    public int getRandomType(){
        return (int)(Math.random()*7+1);
    }

    public boolean isPause() {
        return isPause;
    }

    public void setPause(boolean isPause) {
        this.isPause = isPause;
    }

    /**
     * dto 初始化
     */
    public void dtoInit(){
        gameMap = new int[GAMEZONE_W][GAMEZONE_H];

        //TODO
//        initRecords();
    }

    //TODO
//    private void initRecords(){
//        localData = new DataDisk();
//        List<Player> players = null;
//        try {
//            players = localData.loadData();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        players.sort((o1, o2) -> o2.getPoint() - o1.getPoint());
//        this.diskRecord = players;
//    }
}
