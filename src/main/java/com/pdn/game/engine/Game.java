package com.pdn.game.engine;

import com.pdn.game.engine.ui.ScreenManager;
import com.pdn.game.engine.ui.Window;

import java.awt.Graphics;

public class Game {
    private Window window;
    private ScreenManager screenManager;

    public void start() {
        window.display();

        double deltaTime = 1.0 / 60.0;
        double currentTime = System.currentTimeMillis();
        double accumulator = 0.0;

        while (true) {
            double newTime = System.currentTimeMillis();
            double frameTime = newTime - currentTime;
            currentTime = newTime;

            accumulator += frameTime;

            while (accumulator >= deltaTime) {
                update(deltaTime);
                window.repaint();

                accumulator -= deltaTime;
            }
        }
    }

    public void update(double deltaTime) {
        screenManager.getScreen().update(deltaTime);
    }

    public void render(Graphics graphics) {
        screenManager.getScreen().render(graphics);
    }

    public void setWindow(Window window) {
        this.window = window;
    }

    public void setScreenManager(ScreenManager screenManager) {
        this.screenManager = screenManager;
    }
}
