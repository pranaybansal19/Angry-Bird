package com.game.angrybird;

import com.game.angrybird.Levels.LevelContainers.LevelContainer;

import java.io.*;
import java.util.TreeMap;

public abstract class FileHandler {

    public static void initialize() {
        initializePlayer();
        initializeSavedGame();
    }

    public static void initializeSavedGame(){
        String fileName = "SavedGames.ser";
        File file = new File(fileName);

        if (!file.exists()) {

            TreeMap<String, LevelContainer> map = new TreeMap<>();

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                oos.writeObject(map);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void initializePlayer(){
        String fileName = "Player.ser";
        File file = new File(fileName);

        if (!file.exists()) {

            Player player = new Player();

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                oos.writeObject(player);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    @SuppressWarnings("unchecked")
    public static TreeMap<String, LevelContainer> getSavedGames() {

        String fileName = "SavedGames.ser";

        TreeMap<String, LevelContainer> map = new TreeMap<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            map = (TreeMap<String, LevelContainer>) ois.readObject();
        } catch (EOFException e) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return map;
    }

    public static void addtoSavedGames(TreeMap<String, LevelContainer> map) {

        String fileName = "SavedGames.ser";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static Player getPlayer(){

        String fileName = "Player.ser";

        Player player = new Player();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            player = (Player) ois.readObject();
        } catch (EOFException e) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return player;
    }

    public static void updatePlayer(Player player) {

        String fileName = "Player.ser";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(player);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
