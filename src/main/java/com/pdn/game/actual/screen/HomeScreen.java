package com.pdn.game.actual.screen;

import com.pdn.game.engine.ui.Painter;
import com.pdn.game.engine.ui.Screen;

import java.awt.Font;
import java.awt.Graphics;

import static com.pdn.game.engine.key.KeyManager.getGlobalKeyManager;
import static com.pdn.game.engine.ui.ScreenManager.getGlobalScreenManager;
import static java.awt.Color.WHITE;
import static java.awt.Font.PLAIN;

public class HomeScreen implements Screen {

    @Override
    public void update(double deltaTime) {
        if (getGlobalKeyManager().isKeyPressed("next"))
            getGlobalScreenManager().goTo("battle");
    }

    @Override
    public void render(Graphics graphics) {
        Font oldFont = graphics.getFont();

        graphics.setColor(WHITE);

        graphics.setFont(new Font("SCRUBLAND", PLAIN, 112));
        String titleText = "Mana Koritsu";
        int titleTextWidth = graphics.getFontMetrics().stringWidth(titleText);
        graphics.drawString(titleText, (Painter.WIDTH / 2) - (titleTextWidth / 2), 400);

        graphics.setFont(new Font("SCRUBLAND", PLAIN, 36));
        String playText = "Press [space] to play";
        int playTextWidth = graphics.getFontMetrics().stringWidth(playText);
        graphics.drawString(playText, (Painter.WIDTH / 2) - (playTextWidth / 2), (Painter.HEIGHT / 2) + 100);

        graphics.setFont(oldFont);
    }
}
