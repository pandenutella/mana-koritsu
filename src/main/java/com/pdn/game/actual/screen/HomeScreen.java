package com.pdn.game.actual.screen;

import com.pdn.game.engine.key.KeyManager;
import com.pdn.game.engine.ui.Screen;
import com.pdn.game.engine.ui.ScreenManager;

import java.awt.Graphics;

import static java.awt.Color.BLACK;

public class HomeScreen extends Screen {

    public HomeScreen(ScreenManager screenManager, KeyManager keyManager) {
        super(screenManager, keyManager);
    }

    @Override
    public void update(double deltaTime) {
        if (keyManager.isKeyPressed("next"))
            screenManager.goTo("battle");
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(BLACK);
        graphics.drawString("Home", 30, 30);
        graphics.drawString("Press [SPACE] to play", 30, 45);
    }
}
