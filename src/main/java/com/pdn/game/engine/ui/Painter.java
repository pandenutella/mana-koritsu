package com.pdn.game.engine.ui;

import com.pdn.game.engine.Game;
import lombok.Getter;
import lombok.SneakyThrows;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.net.URL;

import static java.awt.Color.BLACK;
import static java.awt.Font.TRUETYPE_FONT;
import static java.awt.Font.createFont;

@Getter
public class Painter {
    public static int WIDTH = -1;
    public static int HEIGHT = -1;

    private PainterPanel component;

    public void setGame(Game game) {
        component = new PainterPanel(game);
    }

    private static class PainterPanel extends JPanel {
        private final Game game;

        @SneakyThrows
        public PainterPanel(Game game) {
            this.game = game;

            GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
            URL fontFile = getClass().getClassLoader().getResource("SCRUBLAND.ttf");

            graphicsEnvironment.registerFont(createFont(TRUETYPE_FONT, new File(fontFile.toURI())));
        }

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
