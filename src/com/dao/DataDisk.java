package com.dao;

import com.dto.Player;
import com.dto.Player;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 */
public class DataDisk implements Data{

    private List<Player> players = new ArrayList<Player>();
    private final String DATA_PATH = "save/record.dat";

    @Override
    public List<Player> loadData() {
        ObjectInputStream ois = null;
        List<Player> players = new ArrayList<>();
        try {
            ois = new ObjectInputStream(new FileInputStream(DATA_PATH));
            players = (List<Player>) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<Player>();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert ois != null;
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return players;
    }

    @Override
    public void saveData(List<Player> players) {
        ObjectOutputStream oos = null;
        try {
            if (new File(DATA_PATH).exists()) new File(DATA_PATH).delete();
            oos = new ObjectOutputStream(new FileOutputStream(DATA_PATH));
            oos.writeObject(players);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
