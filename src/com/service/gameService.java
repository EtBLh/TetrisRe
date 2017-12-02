package com.service;

import com.assets.blocks.blocksImgAccesser;
import com.dto.GameDto;
import com.processer.processor;
import com.util.ScoreCalculation;
import com.entity.GameAct;
import com.util.SpeedCalculation;
import com.util.msgBox.MsgPane;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.ArrayList;

import com.util.Point;

public class gameService implements Service {

    private GameDto dto;
    private int[][] GameMap;
    private gameAreaService gameAreaService;
    private blocksImgAccesser bImgAccesser = new blocksImgAccesser();
    private com.processer.processor processor;

    /**
     * data showers
     */
    private Label scoreShower;
    private Label levelShower;
    private Label linesShower;
    private ImageView nextShower;
    private ImageView holdShower;
    private AnchorPane componentPane;
    private MsgPane msgPane;

    @Override
    public void setScoreShower(Label scoreShower) {
        this.scoreShower = scoreShower;
    }

    @Override
    public void setLevelShower(Label levelShower) {
        this.levelShower = levelShower;
    }

    @Override
    public void setLinesShower(Label linesShower) {
        this.linesShower = linesShower;
    }

    @Override
    public void setNextShower(ImageView nextShower) {
        this.nextShower = nextShower;
    }

    @Override
    public void setHoldShower(ImageView holdShower) {
        this.holdShower = holdShower;
    }

    @Override
    public void setMsgPane(MsgPane msgPane) {
        this.msgPane = msgPane;
    }

    public gameService(GameDto dto, gameAreaService gameAreaService, AnchorPane componentPane) {
        this.dto = dto;
        this.componentPane = componentPane;
        GameAct act = new GameAct(this.dto.getRandomType());
        dto.setNextBlock(this.dto.getRandomType());
        dto.setGameAct(act);
        updatePreviewAct();
        GameMap = this.dto.getGameMap();
        this.gameAreaService = gameAreaService;
        this.processor = new processor(this);
    }

    @Override
    public void start() {
        this.processor.start();
    }

    private void stop() {
        this.processor.stop();
    }

    private void processDurationUpdate() {
        this.processor.update(SpeedCalculation.getDelay(dto.getLevel()));
    }

    private void reload() {
        this.gameAreaService.repaint();
        this.scoreShower.setText(String.valueOf(dto.getScore()));
        this.levelShower.setText(String.valueOf(dto.getLevel()));
        this.linesShower.setText(String.valueOf(dto.getClearedLines()));

        try {
            this.nextShower.setImage(bImgAccesser.blocks[dto.getNextBlock() - 1]);
            this.holdShower.setImage(bImgAccesser.blocks[dto.getHoldBlock() - 1]);
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
    }

    /**
     * 方塊旋轉
     */
    @Override
    public void ActWhirl() {
        this.dto.getGameAct().whirl(this.dto.getGameMap());
        updatePreviewAct();
        reload();
    }

    /**
     * 方塊下落
     */
    @Override
    public void ActDown() {
        //檢查可否下降方塊
        if (this.dto.getGameAct().move(0, 1, this.dto.getGameMap())) {
            //分數計算
            this.dto.setScore(this.dto.getScore() + 1);
            //更新預覽
            updatePreviewAct();
            reload();
            return;
        }
        if (this.dto.isSkipConcreting()) {
            this.dto.setGhostAct(true);
            this.dto.setDownDelay(true);
            reload();
            this.dto.setSkipConcreting(false);
            return;
        }
        //把方塊加入地圖
        this.addingActIntoMap();

        int RemovedLines_ThisTime = clearLinesControl();
        this.dto.setRemovedLines(this.dto.getClearedLines() + RemovedLines_ThisTime);
        //消行分數計算
        this.dto.setScore(this.dto.getScore() + ScoreCalculation.LINE_CLEARD_SCORE[RemovedLines_ThisTime]);

        //等級計算
        this.dto.setLevel(this.dto.getClearedLines() / 10 + 1);
        //輸
        this.checkLose();
        //製造下一個方塊
        this.produceNewAct();
        //刷新預覽
        this.updatePreviewAct();
        this.processDurationUpdate();
        this.reload();
    }

    /**
     * 方塊向左
     */
    @Override
    public void ActLeft() {
        this.dto.getGameAct().move(-1, 0, this.dto.getGameMap());
        updatePreviewAct();
        reload();
    }

    /**
     * 方塊向右
     */
    @Override
    public void ActRight() {
        this.dto.getGameAct().move(1, 0, this.dto.getGameMap());
        updatePreviewAct();
        reload();
    }

    @Override
    public void HoldAct() {
        int tempType = this.dto.getGameAct().getType();
        if (this.dto.getHoldBlock() == 0) {
            //設定保持方塊
            this.dto.setHoldBlock(tempType);
            //刷新一個新的方塊
            this.dto.getGameAct().init(this.dto.getNextBlock());
            //隨機生成下一個方塊
            this.dto.setNextBlock(this.dto.getRandomType());
            return;
        }
        //設定下落方塊
        this.dto.getGameAct().init(this.dto.getHoldBlock());
        //設定保持方塊
        this.dto.setHoldBlock(tempType);
        //刷新預覽方塊
        this.updatePreviewAct();
        this.reload();
    }

    @Override
    public void ActHardDrop() {
        this.dto.setSkipConcreting(false);
        //設定分數
        this.dto.setScore(this.dto.getScore() + ScoreCalculation.getHardDownScore(updatePreviewAct()));
        for (Point point : this.dto.getPreviewAct().getActPoint()) {
            this.dto.getGameAct().moveTo(this.dto.getPreviewAct().getActPoint());
        }
        this.ActDown();
        reload();

    }

    /**
     * 把方塊加入地圖
     */
    private void addingActIntoMap() {
        //取得遊戲地圖對象
        int[][] map = this.dto.getGameMap();
        //取得方塊位置
        Point[] actPoint = this.dto.getGameAct().getActPoint();
        //把方塊位置放入地圖
        for (int i = 0; i < actPoint.length; i++) {
            map[(int) actPoint[i].getX()][(int) actPoint[i].getY()] = this.dto.getGameAct().getType();
        }
    }

    /**
     * /製造下一個方塊
     */
    private void produceNewAct() {
        //刷新一個新的方塊
        this.dto.getGameAct().init(this.dto.getNextBlock());
        //隨機生成下一個方塊
        this.dto.setNextBlock(this.dto.getRandomType());
    }

    /**
     * 更新預覽
     *
     * @return distance between realAct and PreviewAct
     */
    public int updatePreviewAct() {
        //設定DTO
        this.dto.setPreviewAct(this.dto.getGameAct().getType(), this.dto.getGameAct().getActPoint());
        int distance = 0;
        //移預覽方塊Y軸
        while (this.dto.getPreviewAct().move(0, 1, dto.getGameMap())) distance++;
        return distance;
    }

    /**
     * 消行
     *
     * @return 己消行數
     */
    private int clearLinesControl() {
        int clearedLines = 0;
        ArrayList<Integer> clearLines = new ArrayList<>();
        //查看遊戲地圖是否可以消行
        for (int y = 0; y < GameDto.GAMEZONE_H; y++) {
            //判斷是否可以消行
            if (isCanRemoveLine(y)) {
                clearLines.add(y);
                //已消行數+1
                clearedLines++;
            }
        }
        if (clearedLines <= 0) return 0;
        clearLines(clearLines);
        reload();
        return clearedLines;
    }

    /**
     * 消行處理
     */
    private void clearLines(ArrayList<Integer> clearLines) {
        final int DELAY = 200;
        final int DELAY_AFTER_ANIMATION = 100;

        KeyFrame clear = new KeyFrame(Duration.millis(DELAY + DELAY_AFTER_ANIMATION), e -> {
            this.dto.getclearLinesAnimationStatus().clear();
            clearLines.forEach(integer -> {
                for (int x = 0; x < GameDto.GAMEZONE_W; x++) {
                    for (int y = integer; y > 0; y--) {
                        GameMap[x][y] = GameMap[x][y - 1];
                    }
                    GameMap[x][0] = 0;
                }
            });
            reload();
        });

        KeyFrame animation1 = new KeyFrame(Duration.ZERO, e -> {
            this.dto.getclearLinesAnimationStatus().clear();
            clearLines.forEach(integer -> this.dto.getclearLinesAnimationStatus().add(integer));
            reload();
        });

        KeyFrame recover = new KeyFrame(Duration.millis(DELAY / 2), event -> {
            this.dto.getclearLinesAnimationStatus().clear();
            reload();
        });

        KeyFrame animation2 = new KeyFrame(Duration.millis(DELAY), e -> {
            this.dto.getclearLinesAnimationStatus().clear();
            clearLines.forEach(integer -> this.dto.getclearLinesAnimationStatus().add(integer));
            reload();
        });

        Timeline animator = new Timeline(animation1, recover, animation2, clear);

        animator.play();
    }

    /**
     * 檢查某行是否可以消行
     *
     * @param y 遊戲地圖Y坐標
     * @return 是或否
     */
    private boolean isCanRemoveLine(int y) {
        for (int x = 0; x < GameDto.GAMEZONE_W; x++) {
            //如果有一方格 <=0, 則直接跳到下一行
            if (GameMap[x][y] <= 0) return false;
        }
        return true;
    }

    /**
     * 檢查輸
     */
    private void checkLose() {
        int[][] GameMap = this.dto.getGameMap();
        for (int x = 0; x < GameMap.length; x++) {
            for (int y = 1; y >= 0; y--) {
                if (GameMap[x][y] > 0) {this.lose(); break; }
            }
        }

    }

    private void lose() {
        this.stop();
        this.msgPane.setShow(true);
    }
}
