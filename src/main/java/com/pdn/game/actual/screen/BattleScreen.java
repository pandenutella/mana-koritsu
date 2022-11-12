package com.pdn.game.actual.screen;

import com.pdn.game.engine.key.KeyManager;
import com.pdn.game.engine.ui.Screen;
import com.pdn.game.engine.ui.ScreenManager;

import java.awt.Graphics;

import static java.awt.Color.BLACK;

public class BattleScreen extends Screen {

    public BattleScreen(ScreenManager screenManager, KeyManager keyManager) {
        super(screenManager, keyManager);
    }

    @Override
    public void update(double deltaTime) {
        if (keyManager.isKeyPressed("back"))
            screenManager.goTo("home");
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(BLACK);
        graphics.drawString("Battle", 30, 30);
        graphics.drawString("Press [ESCAPE] to go back", 30, 45);
    }
}
