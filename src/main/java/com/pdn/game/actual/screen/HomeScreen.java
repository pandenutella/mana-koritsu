package com.pdn.game.actual.screen;

import com.pdn.game.engine.ui.Screen;

import java.awt.Graphics;

import static com.pdn.game.engine.key.KeyManager.getGlobalKeyManager;
import static com.pdn.game.engine.ui.ScreenManager.getGlobalScreenManager;
import static java.awt.Color.WHITE;

public class HomeScreen implements Screen {

    @Override
    public void update(double deltaTime) {
        if (getGlobalKeyManager().isKeyPressed("next"))
            getGlobalScreenManager().goTo("battle");
    }

    @Override
    public void render(Graphics graphics) {
        graphics.setColor(WHITE);
        graphics.drawString("Home", 30, 30);
        graphics.drawString("Press [SPACE] to play", 30, 45);
    }
}
