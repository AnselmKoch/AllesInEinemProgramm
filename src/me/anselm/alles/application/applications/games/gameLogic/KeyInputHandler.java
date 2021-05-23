package me.anselm.alles.application.applications.games.gameLogic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class KeyInputHandler implements KeyListener {
    //W=87, D=68, S=83, A=65
    //LEFT = 37, UP = 38, RIGHT = 39, DOWN = 40
    private ArrayList<Integer> pressedKeys = new ArrayList<>();
    private int currentKey;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        currentKey = e.getKeyCode();
        if (!pressedKeys.contains(e.getKeyCode())) {
            pressedKeys.add(e.getKeyCode());

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        currentKey = 0;
        for(int i = 0; i < pressedKeys.size(); i++) {
            if (pressedKeys.get(i) == e.getKeyCode()) {
                pressedKeys.remove(i);
            }
        }
    }

    public ArrayList<Integer> getPressedKeys() {
        return this.pressedKeys;
    }

    public int getCurrentKey() {
        return currentKey;
    }
    public void setCurrentKeyToNull() {
        this.currentKey = 0;
    }
}
