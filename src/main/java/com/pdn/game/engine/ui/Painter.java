package com.pdn.game.engine.ui;

import com.pdn.game.engine.Game;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.swing.JPanel;
import java.awt.Graphics;

import static java.awt.Color.BLACK;

@Getter
public class Painter {
    public static int WIDTH = -1;
    public static int HEIGHT = -1;

    private PainterPanel component;

    public void setGame(Game game) {
        component = new PainterPanel(game);
    }

    @RequiredArgsConstructor
    private static class PainterPanel extends JPanel {
        private final Game game;

        @Override
        protected void paintComponent(Graphics g) {
            if (Painter.WIDTH == -1)
                Painter.WIDTH = getWidth();
            if (Painter.HEIGHT == -1)
                Painter.HEIGHT = getHeight();

            g.setColor(BLACK);
            g.fillRect(0, 0, Painter.WIDTH, Painter.HEIGHT);

            game.render(g);
        }
    }
}
