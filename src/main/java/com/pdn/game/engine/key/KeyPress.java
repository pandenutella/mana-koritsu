package com.pdn.game.engine.key;

import lombok.Getter;

@Getter
public class KeyPress {
    private final String name;

    private boolean pressed = false;
    private double lastInteraction = System.currentTimeMillis();

    public KeyPress(String name) {
        this.name = name;
    }

    public void press() {
        pressed = true;
        lastInteraction = System.currentTimeMillis();
    }

    public void release() {
        pressed = false;
        lastInteraction = System.currentTimeMillis();
    }
}
