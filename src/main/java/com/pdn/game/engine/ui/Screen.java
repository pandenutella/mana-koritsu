package com.pdn.game.engine.ui;

import com.pdn.game.engine.key.KeyManager;

import java.awt.Graphics;

public abstract class Screen {
    protected final ScreenManager screenManager;
    protected final KeyManager keyManager;

    public Screen(ScreenManager screenManager, KeyManager keyManager) {
        this.screenManager = screenManager;
        this.keyManager = keyManager;
    }

    public abstract void update(double deltaTime);

    public abstract void render(Graphics graphics);
}
