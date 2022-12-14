package com.pdn.game.engine;

import com.pdn.game.engine.ui.Window;
import lombok.Setter;

import java.awt.Graphics;

import static com.pdn.game.engine.ui.ScreenManager.getGlobalScreenManager;

@Setter
public class Game {
    public static final String PRIMARY_FONT_NAME = "Game Over";

    private Window window;

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
        getGlobalScreenManager().getScreen().update(deltaTime);
    }

    public void render(Graphics graphics) {
        getGlobalScreenManager().getScreen().render(graphics);
    }
}
