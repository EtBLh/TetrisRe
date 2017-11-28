package com.dao;

import com.dto.Player;
import java.io.IOException;
import java.util.List;

/**
 * 數據持久層介面
 */
public interface Data {

    /**
     * 讀取數據
     */
    List<Player> loadData() throws IOException;

    /**
     * 存儲數據
     */
    void saveData(List<Player> players);

}
